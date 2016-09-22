/**
 * Färbt den Link, der auf das aktuelle Element zeigt, um.
 * (defined: color + elements of class)
 *
 * EventListener: Wenn das Dokument fertig geladen ist.
 *
 * @return Nichts.
 */
window.onload = function recolorNavlink(){
    var hoverColor = "#8BC34A";
    
    /* Je nach geladenem Style, passt eine andere Farbe. */
    if(getCookie("style") == "alternate"){
        hoverColor = "#2196F3";
    }else{
        hoverColor = "#8BC34A";
    }

    //get name of document (*.html)
    var loc = location.pathname.substring(location.pathname.lastIndexOf("/") + 1);
    //get all elements of defined class
    var links = document.getElementsByClassName("navlink");
    for(var i=0;  i < links.length; ++i ){
        //href of element contains name of document?recolor:nothing
        if(links[i].href.indexOf(loc)  > -1){
            links[i].style.color = hoverColor;
        }
    }
};

/**
 * Invertiert die Sichtbarkeit der Elemente des Divs.
 *
 * @param divName, die zubearbeitende Divs.
 *
 * @return Nichts.
 */
function toogleDiv(divName){
    var children = document.getElementsByClassName(divName)[0].children;
    for(var i=0; i < children.length ; i++){
        if(children[i].nodeName != "BUTTON" && children[i].style.display != "none"){
            children[i].style.display = "none";
            document.getElementsByClassName(divName)[0].style.width="auto";
        }
        else if (children[i].nodeName != "BUTTON") {
            children[i].style.display = "block";
        }
    }
}

/**
 * Fügt ein Teaser mehr hinzu.
 *
 * @param divName, das zubearbeitende Div.
 *
 * @return Nichts.
 */
function addDiv(divName){
    var children = document.getElementsByClassName(divName)[0].children;
    var childDivs = [];

    /* Nur die Divs in childDivs übertragen. */
    for(var i = 0, z = 0; i < children.length; ++i){
        if(children[i].nodeName == "DIV"){
            childDivs[z] = children[i];
            ++z;
        }
    }
    /* Das letzte Div, ausser das erste, wird none. */
    var found = false;
    for(i = 0; (i < childDivs.length) && !found; ++i){
        if(childDivs[i].style.display == "none"){
            childDivs[i].style.display = "block";
            found = true;
        }
    }
}

/**
 * Blendet einen Teaser aus.
 *
 * @param divName, das zubearbeitende Div.
 *
 * @return Nichts.
 */
function delDiv(divName){
    var children = document.getElementsByClassName(divName)[0].children;
    var childDivs = [];

    /* Nur die Divs in childDivs übertragen. */
    for(var i = 0, z = 0; i < children.length; ++i){
        if(children[i].nodeName == "DIV"){
            childDivs[z] = children[i];
            ++z;
        }
    }
    /* Das letzte Div, ausser das erste, wird none. */
    var found = false;
    for(i = childDivs.length-1; (i > 0) && !found; --i){
        if(childDivs[i].style.display == "block" || !childDivs[i].style.display){
            childDivs[i].style.display = "none";
            found = true;
        }
    }
}

/**
 * Importiert ein Stylesheet, je nach Zustand des Cookies.#*
 *
 * @return Nichts.
 */
function loadStyle(){
    if(getCookie("style") != "alternate"){
        document.write('<link rel="stylesheet" type="text/css" media="screen, speech" href="formate.css"/>');
    }else{
        document.write('<link rel="stylesheet" type="text/css" media="screen, speech" href="alternate_formate.css"/>');
    }
}

/**
 * Ändert im Cookie die Styleinformationen und lädt die Website aus dem Cache neu.
 *
 * @return Nichts.
 */
function toggleStyle(){
    if(getCookie("style") == "alternate"){
        setCookie("style", "normal");
    }else{
        setCookie("style", "alternate");
    }

    /* Website neu <aus dem Cache> darstellen. */
    location.reload(false);
}

/**
 * Sucht ein Cookie und gibt es zurück.
 *
 * @param cName, der Name des Cookies.
 *
 * @return Das Cookie oder "" für nicht gefunden/undefined.
 */
function getCookie(cName){
    /* Das Cookie wird in Stringteile zerlegt. */
    var cParts = document.cookie.split(';');
    var found = false;
    var toReturn = "";

    /* 2 Zeilen pro Cookie, da jede 2. Zeile die Lebensdauer ist. */
    for(var i = 0; (i < cParts.length) && !found; ++i){
        var cPart = cParts[i];
        /* Makiert den Anfang und das Ende der Cookiezeile. */
        var pos = cPart.indexOf(cName+'=');
        var end = cPart.indexOf(';');

        if(end <= 0){
            end = cPart.length;
        }

        found = (pos != -1);

        if(found){
            /* Ohne Number() tut sich der Parser schwer. */
            toReturn = cPart.substr(Number(Number(pos)+Number(1)+Number(cName.length)), end);
        }
    }

    return toReturn;
}

/**
 * Setzt ein Cookie.
 *
 * @param cName, der Name des Cookies.
 * @param cValue, der Wert des Cookies.
 * @param expDays, die Anzahl an Tagen, die das Cookie gültig ist.
 *
 * @return Nichts.
 */
function setCookie(cName, cValue, expDays){
    var d = new Date();
    d.setTime(d.getTime() + (expDays*24*60*60*1000));
    var expires = "expires="+ d.toUTCString();
    document.cookie = cName + "=" + cValue + ";" + expires;
}

/**
 * Löscht ein Cookie.
 *
 * @param cName, der Name des Cookies.
 *
 * @return Nichts.
 */
function deleteCookie(cName){
    /* Gelöscht wird, indem ein vergangener Zeitpunkt gewählt wird. */
    document.cookie = cName + "=" + "; " + "expires=Thu, 01 Jan 1970 00:00:00 UTC";
}

/**
 * Initialisiert die Slideshow.
 *
 * EventListener: Wenn das Dokument fertig geladen ist.
 *
 * @return Nichts.
 */
window.onload = function addButtons(){
    var i = 0;
    var children = document.getElementsByClassName("about2")[0].children;
    
    var imgL = document.createElement("IMG");
    var imgR = document.createElement("IMG");
    
    var textL = document.createElement('Textarea');
    textL.attribut = 'wert';
    
    imgL.setAttribute("src", 'buttons/btnArwLeft.svg');
    imgR.setAttribute("src", 'buttons/btnArwRight.svg');
    
    imgL.setAttribute("alt", 'Button Links');
    imgR.setAttribute("alt", 'Button Rechts');
    
    var butL = document.createElement('BUTTON');
    var butR = document.createElement('BUTTON');
    
    butL.setAttribute("onclick",'slide("L")');
    butR.setAttribute("onclick",'slide("R")');
    
    butL.setAttribute("id",'fixedButtonL');
    butR.setAttribute("id",'fixedButtonR');
    
    butL.appendChild(imgL);
    butR.appendChild(imgR);
    
    document.body.appendChild(butL);
    document.body.appendChild(butR);
    
    /* Durch die Slides iterieren, damit es Initialisiert wird. */
    for(i = 0; i < children.length+2; ++i){
        slide("L");
    }
}

/**
 * Slided auf about2.html durch die Artikel.
 *
 * @param navigated, die Navigationsrichtung. ('L' oder 'R')
 *
 * @return Nichts.
 */
function slide(navigated){
    var children = document.getElementsByClassName("about2")[0].children;
    var i = 0;
    var found = false;
    
    /* Elemente ein und ausblenden. */
    for(i = 0; (i < children.length) && !found; ++i){
        
        if(children[i].style.display != 'none'){
            
            if(navigated == 'R'){
                if(i >= children.length-1){
                    children[0].style.display = 'block';
                    children[0].style.marginBottom = '3em';
                    children[i].style.display = 'none';
                }
                else{  
                    children[i+1].style.display = 'block';
                    children[i+1].style.marginBottom = '3em';
                    children[i].style.display = 'none';
                } 
                found = true;
            }
            
            if(navigated == 'L'){
                if(i <= 0){
                    children[i].style.display = 'none';
                    children[children.length -1].style.display = 'block';
                    children[i].style.marginBottom = '3em';
                }
                else{  
                    children[i-1].style.display = 'block';
                    children[i].style.display = 'none';
                    children[i-1].style.marginBottom = '3em';
                }
                found = true;
            }
        }
    }
}