# Aufgabe 2 Unix Uebung von Alexander Frankenbusch
#!/bin/bash
grep -i -v "error"|sort -t ":" -rk3,3 -rk2,2 -n|tee "$1"|tail -n 1|cut -d : -f1
# grep ignoriert jede Zeile in der Error steht. Case unabhaengig. -i ignoriert case und v gibt wieder
# sort sortiert . -t fÃ¼r trennsymbol ":" , wir sortieren nach der 3. spalte , anschliessend nach der 2. spalte und -n fuer nummerisch
# tee  ausgaben/schreiben
# tail , letzte Zeile -n = zeile 1
# cut gibt Text Zeilenweise aus auf der Konsole .-d = delimiter ":" . fl steht fuer field
