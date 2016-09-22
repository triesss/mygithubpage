#!/bin/bash
#-------------Funktionen-----------------
#-h|--help - prints the help text
function printHelp () {
    echo "Usage:
textmanipulator.sh -h | textmanipulator.sh --help

prints this help and exits

textmanipulator.sh OP CHAR1 [CHAR2] ...

provides a simple text manipulation tool. A call consists of one to indefinite
calls of the following OPs and their parameters. The text is read from stdin
and the manipulated text is written to stdout.

OP is one of:

  -r|--replace - replaces all characters CHAR1 in the given text with CHAR2.
                 CHAR1 and CHAR2 have to be letters (A-Z,a-z).

  -d|--delete - deletes all characters CHAR1 in the given text. CHAR1 has to
                be a letter (A-Z,a-z).

  -v|--replace-vowels - replaces all vowels in the given text with CHAR1.
                        CHAR1 has to be a vowel (A,E,I,O,U,a,e,i,o,u)."
}
#-d|--delete - deletes all characters CHAR1 in the given text. CHAR1 has to
#              be a letter (A-Z,a-z).
function delete () {
    echo "$1" | tr -d "$2"
}
#-r|--replace - replaces all characters CHAR1 in the given text with CHAR2.
#               CHAR1 and CHAR2 have to be letters (A-Z,a-z).
function replace () {
    echo "$1" | tr "$2" "$3"
}
#-v|--replace-vowels - replaces all vowels in the given text with CHAR1.
#                      CHAR1 has to be a vowel (A,E,I,O,U,a,e,i,o,u).
function replaceVowels () {
    echo "$1" | tr "A,E,I,O,U,a,e,i,o,u" "$2"
}
#PrÃ¼ft, ob die Ã¼bergebenen Parameter den Funktionen entsprechen
#einstelliger character
#character in [a-zA-Z]
function validParameter () {
    for (( i = 1; i <= "$#" ; i++ )); do
        eval parameter=\$$i
        if [[ $parameter != [a-zA-Z] ]]; then
            echo 1
            exit 1
        fi
    done
    echo 0
}
#-------------Aufrufe-----------------
#Speichert das gepipte echo in stdin
if test  /dev/stdin ; then
    stdin=$(cat)
fi
#Wenn ein -h|--help mit Parametern oder im verketteten Aufruf stehen: Error 1
if [[ "$@" == *" --help"* ]] || [[ "$@" == *" -h"* ]]; then
    (>&2 echo "-h|--help detected")
    >&2 printHelp
    exit 1
fi
while [[ $@ ]]; do
    case $1 in
        -h|--help )
            if [[ "$#" != "1" ]]; then
                (>&2 echo "Error: Wrong/missing operators")
                >&2 printHelp
                exit 1
            else
                printHelp
                exit 0
            fi
        ;;
        -d|--delete )
            if [[ $(validParameter "$2") != 0 ]]; then
                (>&2 echo "Error: CHAR needs to be a single letter [a-zA-Z]")
                (>&2 printHelp)
                exit 3
            else
                stdin=$(delete "$stdin" "$2")
                shift 2
            fi
        ;;
        -r|--replace )
            if [[ $(validParameter "$2") != 0 ]] || [[ $(validParameter "$3") != 0 ]]; then
                (>&2 echo "Error: CHAR needs to be a single letter [a-zA-Z]")
                (>&2 printHelp)
                exit 3
            else
                stdin=$(replace "$stdin" "$2" "$3")
                shift 3
            fi
        ;;
        -v|--replace-vowels )
            if [[ $2 != [A,E,I,O,U,a,e,i,o,u] ]]; then
                (>&2 echo "Error: CHAR needs to be a single vowel")
                (>&2 printHelp)
                exit 4
            else
                stdin=$(replaceVowels "$stdin" "$2")
                shift 2
            fi
        ;;
        * )
            (>&2 echo "Error: Wrong/missing operators")
            >&2 printHelp
            exit 1
        ;;
    esac
done
echo $stdin
exit 0
