#!/bin/bash

cd "$*"
rm -rf STS
ln -s STS.app/Contents/MacOS/STS ./STS

# p2 has a bug (ah, more then one actually): it can't make its mind if it wants 
# sts.ini or STS.ini so on case sensitive file systens we copy it file again

# The following checks if the sts.ini file exists already. This is only fails on 
# case-sensitive file systems; we should duplicate the ini to make p2 happy
if [ ! -e ./STS.app/Contents/MacOS/sts.ini ]; then 
	cp ./STS.app/Contents/MacOS/STS.ini ./STS.app/Contents/MacOS/sts.ini; 
fi
