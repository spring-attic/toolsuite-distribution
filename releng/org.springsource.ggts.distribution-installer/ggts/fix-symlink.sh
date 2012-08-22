#!/bin/bash

cd "$*"
rm -rf GGTS
ln -s GGTS.app/Contents/MacOS/GGTS ./GGTS

# p2 has a bug (ah, more then one actually): it can't make its mind if it wants 
# sts.ini or STS.ini so on case sensitive file systens we copy it file again

# The following checks if the sts.ini file exists already. This is only fails on 
# case-sensitive file systems; we should duplicate the ini to make p2 happy
if [ ! -e ./GGTS.app/Contents/MacOS/ggts.ini ]; then 
	cp ./GGTS.app/Contents/MacOS/GGTS.ini ./GGTS.app/Contents/MacOS/ggts.ini; 
fi
