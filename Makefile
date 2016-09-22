SHELL=/bin/bash
SIZE=100

define HELPTEXT
"Makefile to convert dot and svg to png files.

VARIABLES
  SIZE  - specifies the largest thumbnail size in pixel (default=100)

TARGETS
  all   - default target, does the same as html target
  png   - converts all dot and svg files to png files
  thumb - creates all png files and thumbnails
  html  - creates all png files, thumbnails and generates html page
  help  - display this help and exit
  clean - remove all generated files"
endef

SVGSOURCES=$(wildcard *.svg)
DOTSOURCES=$(wildcard *.dot)
PNGSOURCES=$(wildcard *.png)

GENPNG=	$(SVGSOURCES:.svg=.png) \
		$(DOTSOURCES:.dot=.png)
GENTHUMB= $(GENPNG:.png=.thumb.gif) \
		  $(PNGSOURCES:.png=.thumb.gif)

.PHONY	: all clean help png thumb html
all		: html
help	: $(info $(HELPTEXT))
png		: $(GENPNG)
thumb	: $(GENTHUMB)
html	: thumb
		@echo "<DOCTYPE HTML>" > index.html
		@echo "<head>" >> index.html
		@echo "<title>Unix Aufgabe 5</title>" >> index.html
		@echo "<head>" >> index.html
		@$(foreach genthu,$(GENTHUMB),echo "<img src=\""$(genthu)"\"/><br/>" >> index.html; )
		@echo "</head>" >> index.html
#default rules
%.png	: %.svg
		@convert $< $@
%.png	: %.dot
		@dot -Tpng $< > $@
%.thumb.gif: %.png
		@convert -resize $(SIZE)x$(SIZE)\> $< $@

#clean up
clean	:
		@-rm -f $(GENPNG) $(GENTHUMB) index.html
