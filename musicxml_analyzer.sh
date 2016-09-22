#!/bin/bash
#von Alexander Frankenbusch
#Programm kann Titel und Mitwirkende, Instrumente sowie mit einer ID bekannte Instrumente aus einer XML-Datei ausgeben.
#Eine Input- und eine Outputdatei kann gesetzt werden.
#gibt die Hilfe aus
function printHelp () {
    echo "Usage:
  musicxml_analyzer -h | musicxml_analyzer --help
    prints this help and exits

  - or -

  musicxml_analyzer [OPTS]
  where OPTS is one or more of
    -i | --input FILE               sets the inputfile; if not set,
                                    input is read from stdin.

    -o | --output FILE              sets the outputfile; if not set,
                                    output is written to stdout.

    -c | --creators                 prints the title and all creators,
                                    sorted by appearance.

    -m | --instruments              prints all instruments, sorted by name.

    -s | --specific-instrument ID   prints the instrument with the given ID
                                    or nothing, if no instrument with that ID exists."
}
#----------------------Input handling----------------------------
#Variable ERRORCODE wird auf 0 gesetzt fÃ¼r Ausgabe der Fehlermeldung (hier keine)
ERRORCODE=0
#Ausgabe der Hilfe und Beendigung ohne Fehler, wenn einziger Parameter -h oder --help ist
if ([[ $1 == "-h" ]] || [[ $1 == "--help" ]]) && [[ $# -eq 1 ]]; then
    printHelp
    exit 0
fi
#solange Parameter vorhanden sind und das Programm nicht durch einen Fehler beendet wurde, Auswertung der Parameter
while [[ $# -ne 0 ]] && [[ $ERRORCODE == 0 ]] ; do
    case $1 in
#kann Dateiname nicht auch mit "-" beginnen?
#wenn ein zweiter Parameter vorhanden ist und er keine Operation auf das Dokument ist, wird $2 als Inputfile gesetzt und IFLAG auf 1 fÃ¼r Wahl der Inputquelle, ansonsten Fehlermeldung
        -i | --input )
            if [[ $2 != "-"* ]] && [[ $2 ]]; then
                FILEIN="$2"
                IFLAG=1
            else
                ERRORCODE=2
            fi
#die bereits abgearbeiteten Parameter werden durch shift entfernt 
            shift
            shift
        ;;
#wenn zweiter Parameter vorhanden ist und keine Operation, Setzen von $2 als Outputfile und 
        -o | --output )
            if [[ $2 != "-"* ]] && [[ $2 ]]; then
                FILEOUT="$2"
                OFLAG=1
            else
                ERRORCODE=2
            fi
            shift
            shift
        ;;
        -c | --creators )
            CFLAG=1
            shift
        ;;
        -m | --instruments )
            MFLAG=1
            shift
        ;;
        -s | --specific-instrument )
            if [[ $2 != "-"* ]] && [[ $2 ]]; then
                ID="$2"
                SFLAG=1
            else
                ERRORCODE=2
            fi
            shift
            shift
        ;;
        * )
            ERRORCODE=1
        ;;
    esac
done
if [[ $IFLAG != 1 ]]; then
    FILEIN="$(cat)"
fi
if ! test -r "$FILEIN" ; then
    ERRORCODE=3
fi
if [[ $OFLAG == 1 ]] && ! test -w "$FILEOUT" ; then
    touch "$FILEOUT"
fi
#----------------------Error handling----------------------------
case $ERRORCODE in
    1 )
        (>&2 echo "Error: Wrong/missing operators")
        (>&2 printHelp)
        exit 1
        ;;
    2 )
        (>&2 echo "Error: Wrong parameter.")
        (>&2 printHelp)
        exit 2
        ;;
    3 )
        (>&2 echo "Error: File does not exist/is not readable.")
        (>&2 printHelp)
        exit 3
        ;;
esac
#----------------------Flag handling----------------------------
MODFILEIN=$(cat "$FILEIN" | tr -d "\n")
MODFILEIN=${MODFILEIN//<movement-title>/\\nFOUNDTITLE:}
MODFILEIN=${MODFILEIN//<\/movement-title>/\\n}
if [[ $MFLAG == 1 ]] || [[ $SFLAG == 1 ]]; then
    MODFILEIN=${MODFILEIN//<part-name>/\\nFOUNDPARTNAME:}
    MODFILEIN=${MODFILEIN//<\/part-name>/\\n}
fi
if [[ $CFLAG == 1 ]]; then
    MODFILEIN=${MODFILEIN//<creator type=\"/\\nFOUNDCREATOR:}
    MODFILEIN=${MODFILEIN//<\/creator>/\\n}
fi
if [[ $SFLAG == 1 ]]; then
    MODFILEIN=${MODFILEIN//<score-part id=\"/\\nFOUNDPARTID:}
    MODFILEIN=${MODFILEIN//<\/score-part>/\\n}
fi
MODFILEIN=$(echo -e $MODFILEIN)
while read LINE; do
    if [[ $LINE == "FOUNDTITLE:"* ]]; then
        LINE=$(echo $LINE | cut -d: -f2)
        TITLE=$LINE
    elif [[ $LINE == "FOUNDPARTNAME:"* ]]; then
        LINE=$(echo $LINE | cut -d: -f2)
        PARTNAME+=$(echo $LINE | tr -d "\r")
        PARTNAME+=\\n
    elif [[ $LINE == "FOUNDPARTID:"* ]]; then
        LINE=$(echo $LINE | cut -d: -f2)
        PARTID+=$(echo $LINE | cut -d\" -f1)
        PARTID+=\\n
    elif [[ $LINE == "FOUNDCREATOR:"* ]]; then
        LINE=$(echo $LINE | cut -d: -f2)
        TYPE+=$(echo $LINE | grep -oP ".*(?=\">)")
        TYPE+=\\n
        CREATOR+=$(echo $LINE | grep -oP "(?<=\"\>).*" | tr -d "\r")
        CREATOR+=\\n
    fi
done <<< "$MODFILEIN"
#----------------------Output handling----------------------------
if [[ $CFLAG == 1 ]]; then
    OUTPUT+="Title: $TITLE\\n"
    OUTPUT+="- by -\\n"
    COMBINED=$(paste <(echo -e "$TYPE") <(echo -e "$CREATOR"))
    while read a b; do
        if [[ $(echo "$a" | wc -w ) -ne 0 ]]; then
            OUTPUT+="$a: $b\\n"
        fi
    done <<< "$COMBINED"
    if [[ $MFLAG == 1 ]] || [[ $SFLAG == 1 ]]; then
        OUTPUT+="\\n"
    fi
fi
if [[ $MFLAG == 1 ]]; then
    PARTNAMESORTED=$(echo -e "$PARTNAME" | sort)
    OUTPUT+="Instruments:\\n"
    while read PART; do
        if [[ $(echo "$PART" | wc -w ) -ne 0 ]]; then
            OUTPUT+="$PART\\n"
        fi
    done <<< "$PARTNAMESORTED"
    if [[ $SFLAG == 1 ]]; then
        OUTPUT+="\\n"
    fi
fi
if [[ $SFLAG == 1 ]]; then
    COMBINED=$(paste <(echo -e "$PARTID") <(echo -e "$PARTNAME"))
    OUTPUT+="Instrument with ID \"$ID\":\\n"
    while read x y; do
        if [[ "$x" == "$ID" ]]; then
            OUTPUT+="$y\\n"
        fi
    done <<< "$COMBINED"
fi
if [[ $OFLAG == 1 ]]; then
    echo -e "$OUTPUT" > "$FILEOUT"
else
    echo -e "$OUTPUT"
fi
