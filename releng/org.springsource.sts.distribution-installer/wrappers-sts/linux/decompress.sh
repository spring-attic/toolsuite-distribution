#!/bin/bash

cpad() {
		word="$1"
		padding=" "
		len=77
        while [ ${#word} -lt $len ]; do
                word="$word$padding";
                if [ ${#word} -lt $len ]; then
                        word="$padding$word"
                fi;
        done;
        while [ ${#word} -gt $len ]; do
                word=${word:0:$((${#word}-1))}
                if [ ${#word} -gt $len ]; then
                        word=${word:1:$((${#word}-1))}
                fi;
        done;
        echo "*$word*";
}

echo ""
echo "*******************************************************************************"
cpad "Welcome to the"
cpad "Spring Tool Suite $STS_VERSION"
cpad "Installer"
echo "*******************************************************************************"
echo ""
printf " preparing the installer..."
export TMPDIR=`mktemp -d /tmp/selfextract.XXXXXX`
ARCHIVE=`awk '/^__ARCHIVE_BELOW__/ {print NR + 1; exit 0; }' $0`

tail -n+$ARCHIVE $0 | tar xz -C $TMPDIR
printf " done\n"

CDIR=`pwd`
cd $TMPDIR
printf " starting UI installer. please follow instructions on screen..."
./installer.sh
printf " done\n"
printf " cleaning up..."
cd $CDIR
rm -rf $TMPDIR
printf " done\n"
echo ""
echo "*******************************************************************************"
cpad "For more information vist:"
cpad "http://spring.io/tools"
echo "*******************************************************************************"
echo ""
exit 0

__ARCHIVE_BELOW__
