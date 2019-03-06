REM Generates windows .ico file using 'imagemagick' commandline tool.
REM While this tool is really a unix tool a version of it is availabe for windows.
REM This bat file needs to be run on a windows system.
REM Similar command should also work on Linux but for
REM some reason the version of imagemagick I had on Linux refused to store
REM The 256x256 image uncompressed.
REM See https://www.imagemagick.org/discourse-server/viewtopic.php?f=1&t=24883

convert sts256.png -compress none ^
    "(" -clone 0 -resize 16x16 -compress none ")" ^
    "(" -clone 0 -resize 32x32 -compress none ")" ^
    "(" -clone 0 -resize 48x48 -compress none ")" ^
    "(" -clone 0 -resize 16x16 -colors 256 -compress none ")" ^
    "(" -clone 0 -resize 32x32 -colors 256 -compress none ")" ^
    "(" -clone 0 -resize 48x48 -colors 256 -compress none ")" ^
    "(" -clone 0 -resize 256x256 -compress none ")" ^
    -delete 0 sts.ico

REM Below are some useful commands to verify the output, uncomment them to use
REM identify sts.ico
REM identify -verbose sts.ico > sts.ico.info
