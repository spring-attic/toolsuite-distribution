#!/bin/bash
#set -x

export COPYFILE_DISABLE=true

PACK_PROPERTIES="pack.excludes=/**/dm-server-*/boot/com.springsource.server.bootstrap.harness-*.jar,/**/sts-*/plugins/org.eclipse.jdt.apt.pluggable.core_*.jar,/**/sts*/plugins/org.eclipse.jdt.compiler.apt_*.jar,/**/sts*/plugins/org.eclipse.jdt.compiler.tool_*.jar,/**/spring-roo-*/dist/org.springframework.roo.annotations-*-sources.jar,/**/sts-*/p2/org.eclipse.equinox.p2.repository/cache/*.jar,/**/sts-*/plugins/com.ibm.icu_*.jar,/**/sts-*/plugins/org.apache.xalan_*.jar,/**/sts-*/plugins/org.sat4j.pb_*.jar,/**/sts-*/plugins/org.sat4j.core_*.jar,/**/sts-*/plugins/org.maven.ide.eclipse*.jar,/**/sts-*/plugins/org.sonatype.tycho.m2e_*.jar,/**/sts-*/plugins/org.eclipse.jem.proxy_*.jar,/**/sts-*/plugins/com.springsource.org.aspectj.weaver_*.jar,/**/sts-*/plugins/org.eclipse.jst.jsf.core_*.jar"

processTar() {
	echo Lauchning pack200 processing $1.gz for $2
	
	if [ ! -d "$2" ]; then
		rm -rf $2
		mkdir $2
		cd $2
		tar zxf ../../../org.springsource.sts.distribution-bundles/target/$1.gz
		
		if [[ $2 =~ .*linux.* ]]; then
			JDKPLACEHOLDER=`grep JDKPath ./springsource/$STS_DIRECTORY-$STS_VERSION/$STS_APPNAME.ini`
			if [ -z $JDKPLACEHOLDER ] ; then
				echo "-vm" >> ./temp_STS.ini
				echo "\$JDKPath/bin/java" >> ./temp_STS.ini
				cat ./temp_STS.ini ./springsource/$STS_DIRECTORY-$STS_VERSION/$STS_APPNAME.ini >> ./springsource/$STS_DIRECTORY-$STS_VERSION/temp_STS.ini
				rm -rf ./temp_STS.ini
				mv ./springsource/$STS_DIRECTORY-$STS_VERSION/temp_STS.ini ./springsource/$STS_DIRECTORY-$STS_VERSION/$STS_APPNAME.ini
			fi
		fi
				
#		nohup $JAVA_HOME/bin/java -cp ../../installer/izpack/bin/customActions/Unpack200InstallListener.jar -Xmx2024m com.springsource.sts.jarprocessor.Main -processAll -pack -repack -lzma -verbose -remove -outputDir springsource springsource > $2.log &

		cd ..
	fi

	echo ".... done"
}

createInstallerTar() {
	echo Creating installer for $1.gz

	cd $2
	
	cp -r ../../$INSTALLER_DIR/* .
	
	if [[ $OS == *Darwin* ]]
	then
		sed -i '' -e 's/%sts.version%/'$STS_VERSION'/g' ./win-shortcut-spec.xml
	else
		sed -i -e 's/%sts.version%/'$STS_VERSION'/g' ./win-shortcut-spec.xml
	fi
	
	
	if [[ $2 =~ .*macosx.* ]]; then
		mv ./fix-symlink.sh ./springsource/$STS_DIRECTORY-$STS_VERSION/
	fi
		
	ant -Dsts.version=$STS_VERSION -Dtc.server.version=$TCS_VERSION -Droo.version=$ROO_VERSION -Dgrails.version=$GRAILS_VERSION -Dmaven.version=$MAVEN_VERSION -Dtomcat6.version=$TOMCAT6_VERSION -Dtomcat7.version=$TOMCAT7_VERSION -Ddistribution.name=$3 -Dos.system=$4 -Djdk.minversion=$5 -Djdk.architecture=$6 -Dsts.platform=$2
		
	cd ..

	echo ".... done"
}

wrapInstallerDmg() {
	echo Wrapping installer for $1.jar
	
	APP_NAME="Installer - "$STS_PRODUCT_NAME" "$STS_VERSION".app"
		
	rm -rf installer/izpack/utils/wrappers/izpack2app/Mac-App-Template/*
	cp -r ../wrappers-$INSTALLER_DIR/mac/app-template/* installer/izpack/utils/wrappers/izpack2app/Mac-App-Template/
	find installer/izpack/utils/wrappers/izpack2app/Mac-App-Template -name .svn | xargs rm -rf 
	
	python installer/izpack/utils/wrappers/izpack2app/izpack2app.py $1.jar "$APP_NAME"
	cp ../wrappers-$INSTALLER_DIR/mac/installer-template.dmg.gz .
	gunzip ./installer-template.dmg.gz
	
	mkdir -p ./installer-dmg
	
	if [ $SKIP_OSX_SIGNING == true ]
	then
		codesign -s 'Developer ID Application: VMware, Inc' "$APP_NAME"
	fi
	
	if [[ $OS == *Darwin* ]]
	then
		hdiutil attach ./installer-template.dmg -mountpoint ./installer-dmg
	else
		mount -o loop -t hfsplus ./installer-template.dmg ./installer-dmg
	fi
	
	rm -rf "./installer-dmg/Installer*"
	cp -r "$APP_NAME" ./installer-dmg
	rm -rf "$APP_NAME"
	
	if [[ $OS == *Darwin* ]]
	then
		hdiutil detach ./installer-dmg
		mv installer-template.dmg installer-template-uncompressed.dmg
		hdiutil convert ./installer-template-uncompressed.dmg -format UDZO -o installer-template.dmg
		rm installer-template-uncompressed.dmg
	else
		umount ./installer-dmg
	fi
	mv ./installer-template.dmg $2.dmg
	
	echo ".... done"
}

wrapInstallerSh() {
	echo Wrapping installer for $1.jar
		
	cp -r ../wrappers-$INSTALLER_DIR/linux/* .
	
	if [[ $OS == *Darwin* ]]
	then
		sed -i '' -e 's/$JAR_NAME/'$1'/g' ./installer.sh
		sed -i '' -e 's/$STS_VERSION/'$STS_VERSION'/g' ./decompress.sh
	else
		sed -i -e 's/$JAR_NAME/'$1'/g' ./installer.sh
		sed -i -e 's/$STS_VERSION/'$STS_VERSION'/g' ./decompress.sh
	fi
	
	tar czf payload.tar.gz ./installer.sh $1.jar
	
	cat decompress.sh payload.tar.gz > $1.sh
	
	rm decompress.sh installer.sh payload.tar.gz
	
	echo ".... done"
}

processZip() {
	echo Lauchning pack200 processing $1 for $2

	if [ ! -d "$2" ]; then
		rm -rf $2
		mkdir $2
		cd $2
		unzip -q ../../../org.springsource.sts.distribution-bundles/target/$1
		
		JDKPLACEHOLDER=`grep JDKPath ./springsource/$STS_DIRECTORY-$STS_VERSION/$STS_APPNAME.ini`
		if [ -z $JDKPLACEHOLDER ] ; then
			echo "-vm" >> ./temp_STS.ini
			echo "\$JDKPath\bin\javaw.exe" >> ./temp_STS.ini
			cat ./temp_STS.ini ./springsource/$STS_DIRECTORY-$STS_VERSION/$STS_APPNAME.ini >> ./springsource/$STS_DIRECTORY-$STS_VERSION/temp_STS.ini
			rm -rf ./temp_STS.ini
			mv ./springsource/$STS_DIRECTORY-$STS_VERSION/temp_STS.ini ./springsource/$STS_DIRECTORY-$STS_VERSION/$STS_APPNAME.ini
		fi
		
#		nohup $JAVA_HOME/bin/java -cp ../../installer/izpack/bin/customActions/Unpack200InstallListener.jar -Xmx2024m com.springsource.sts.jarprocessor.Main -processAll -pack -repack -lzma -verbose -remove -outputDir springsource springsource > $2.log &

		cd ..
	fi
	
	echo ".... done"
}

createInstallerZip() {
	echo Creating installer for $1

	cd $2

	cp -r ../../$INSTALLER_DIR/* .
	if [[ $OS == *Darwin* ]]
	then
		sed -i '' -e 's/%sts.version%/'$STS_VERSION'/g' ./win-shortcut-spec.xml
	else
		sed -i -e 's/%sts.version%/'$STS_VERSION'/g' ./win-shortcut-spec.xml
	fi

	ant -Dsts.version=$STS_VERSION -Dtc.server.version=$TCS_VERSION -Droo.version=$ROO_VERSION -Dgrails.version=$GRAILS_VERSION -Dmaven.version=$MAVEN_VERSION -Dtomcat6.version=$TOMCAT6_VERSION -Dtomcat7.version=$TOMCAT7_VERSION -Ddistribution.name=$3 -Dos.system=$4 -Djdk.minversion=$5 -Djdk.architecture=$6 -Dsts.platform=$2
		
	cd ..

	echo ".... done"
}

wrapInstallerZip() {
	echo Wrapping installer for $1.jar
	
	DIR=`pwd`
	cd ../wrappers-$INSTALLER_DIR/win/app-template
	
	cp install-template.xml install.xml
	STS_MAJOR_VERSION=`expr "$STS_VERSION" : '\([0-9]\.[0-9]\.[0-9]\).*'`
    SHORT_NAME=`expr "$1" : '$STS_SHORTNAME-\(.*\)-installer'`	

	if [[ $OS == *Darwin* ]]
	then
		sed -i '' -e 's/%sts.version%/'$STS_VERSION'/g' ./install.xml
		sed -i '' -e 's/%sts.major.version%/'$STS_MAJOR_VERSION'/g' ./install.xml
		sed -i '' -e 's/%sts.internal.name%/sts-'$SHORT_NAME'.jar/g' ./install.xml
		sed -i '' -e 's/%sts.file.name%/sts-'$SHORT_NAME'.exe/g' ./install.xml
	else
		sed -i -e 's/%sts.version%/'$STS_VERSION'/g' ./install.xml
		sed -i -e 's/%sts.major.version%/'$STS_MAJOR_VERSION'/g' ./install.xml
		sed -i -e 's/%sts.internal.name%/sts-'$SHORT_NAME'.jar/g' ./install.xml
		sed -i -e 's/%sts.file.name%/sts-'$SHORT_NAME'.exe/g' ./install.xml
	fi

	ant -Dinstaller.jar=$DIR/$1.jar -Dinstaller.exe=$DIR/$1.exe
	
	rm install.xml
	cd $DIR

	echo ".... done"
}

OS=`uname -a`

mkdir installer
tar zxf ../../../common/izpack.tar.gz
mv izpack installer/

if [[ $OS == *Darwin* ]]
then
	tar zxf ../../../common/launch4j-macosx.tar.gz
else
	tar zxf ../../../common/launch4j-linux.tar.gz
fi
mv launch4j installer/

export IZPACK_HOME=`pwd`/installer/izpack
export LAUNCH4J_HOME=`pwd`/installer/launch4j

#ZIP_NAME=`ls ../../org.springsource.sts.distribution-bundles/target/spring-tool-suite-*-macosx-cocoa*`
#RELEASE_VERSION=`expr "$ZIP_NAME" : '.*\([0-9]\.[0-9]*\.[0-9]*\.[A-Z,a-z,0-9]*\).*'`
#STS_VERSION=$RELEASE_VERSION
#ECLIPSE_VERSION=`expr "$ZIP_NAME" : '.*-\(e[.0-9]*\)-.*'`

RELEASE_VERSION=$1
STS_VERSION=$1
ECLIPSE_VERSION=$2

echo sts.version=$STS_VERSION

#exit

# SPRING TOOL SUITE
STS_DIRECTORY=sts
STS_APPNAME=STS
STS_PRODUCT_NAME='Spring Tool Suite'
STS_SHORTNAME=spring-tool-suite
INSTALLER_DIR=sts

processTar spring-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-macosx-cocoa.tar macosx.cocoa 
processTar spring-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-macosx-cocoa-x86_64.tar macosx.cocoa.x86_64 
processTar spring-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-linux-gtk.tar linux 
processTar spring-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-linux-gtk-x86_64.tar linux.x86_64 
processZip spring-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-win32.zip win32 
processZip spring-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-win32-x86_64.zip win32.x86_64


ZIP_NAME=`ls -a macosx.cocoa/springsource/ | grep tc-server-developer`
TCS_VERSION=`expr "$ZIP_NAME" : '.*\([0-9]\.[0-9]*\.[0-9]*\.[-,A-Z,a-z,0-9]*\).*'`

ZIP_NAME=`ls -a macosx.cocoa/springsource/vfabric-tc-server-developer-$TCS_VERSION/ | grep tomcat-6`
TOMCAT6_VERSION=`expr "$ZIP_NAME" : '.*\([0-9]\.[0-9]*\.[0-9]*\.[-,A-Z,a-z,0-9,.]*\).*'`

ZIP_NAME=`ls -a macosx.cocoa/springsource/vfabric-tc-server-developer-$TCS_VERSION/ | grep tomcat-7`
TOMCAT7_VERSION=`expr "$ZIP_NAME" : '.*\([0-9]\.[0-9]*\.[0-9]*\.[-,A-Z,a-z,0-9,.]*\).*'`

ZIP_NAME=`ls -a macosx.cocoa/springsource/ | grep roo`
ROO_VERSION=`expr "$ZIP_NAME" : '.*\([0-9]\.[0-9]*\.[0-9]*\.[A-Z,a-z,0-9]*\).*'`

#ZIP_NAME=`ls -a macosx.cocoa/ | grep grails`
#GRAILS_VERSION=`expr "$ZIP_NAME" : '.*\([0-9]\.[0-9]\+\.[0-9]\+\.\?[A-Z,a-z,0-9]*\).*'`

ZIP_NAME=`ls -a macosx.cocoa/springsource/ | grep maven`
MAVEN_VERSION=`expr "$ZIP_NAME" : '.*\([0-9]\.[0-9]\.[0-9]\).*'`
#MAVEN_VERSION=3.0.4

echo sts.version=$STS_VERSION
echo tcs.version=$TCS_VERSION
echo tomcat6.version=$TOMCAT6_VERSION
echo tomcat7.version=$TOMCAT7_VERSION
echo roo.version=$ROO_VERSION
echo grails.version=$GRAILS_VERSION
echo maven.version=$MAVEN_VERSION


createInstallerTar spring-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-macosx-cocoa.tar macosx.cocoa spring-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-macosx-cocoa izpack.macinstall 1.5.0 32bit
createInstallerTar spring-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-macosx-cocoa-x86_64.tar macosx.cocoa.x86_64 spring-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-macosx-cocoa-x86_64 izpack.macinstall 1.6.0 64bit

createInstallerTar spring-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-linux-gtk.tar linux spring-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-linux-gtk izpack.linuxinstall 1.5.0 32bit
createInstallerTar spring-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-linux-gtk-x86_64.tar linux.x86_64 spring-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-linux-gtk-x86_64 izpack.linuxinstall 1.5.0 64bit

createInstallerZip spring-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-win32.zip win32 spring-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-win32 izpack.windowsinstall 1.5.0 32bit
createInstallerZip spring-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-win32-x86_64.zip win32.x86_64 spring-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-win32-x86_64 izpack.windowsinstall 1.5.0 64bit

wrapInstallerDmg spring-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-macosx-cocoa-installer spring-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-macosx-cocoa-installer
wrapInstallerDmg spring-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-macosx-cocoa-x86_64-installer spring-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-macosx-cocoa-x86_64-installer

wrapInstallerSh spring-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-linux-gtk-installer
wrapInstallerSh spring-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-linux-gtk-x86_64-installer

wrapInstallerZip spring-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-win32-installer
wrapInstallerZip spring-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-win32-x86_64-installer

rm -rf macosx.cocoa
rm -rf macosx.cocoa.x86_64
rm -rf linux
rm -rf linux.x86_64
rm -rf win32
rm -rf win32.x86_64

echo "...creating checksum files"
for II in *tool-suite*; do 
	openssl dgst -md5 $II >>$II.md5;
	openssl dgst -sha1 $II >>$II.sha1;
done


# GROOVY/GRAILS TOOL SUITE
#STS_DIRECTORY=ggts
#STS_APPNAME=GGTS
#STS_PRODUCT_NAME='Groovy-Grails Tool Suite'
#STS_SHORTNAME=groovy-grails-tool-suite
#INSTALLER_DIR=ggts

#processTar groovy-grails-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-macosx-cocoa.tar gg-macosx.cocoa 
#processTar groovy-grails-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-macosx-cocoa-x86_64.tar gg-macosx.cocoa.x86_64 
#processTar groovy-grails-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-linux-gtk-x86_64.tar gg-linux.x86_64 
#processTar groovy-grails-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-linux-gtk.tar gg-linux 
#processZip groovy-grails-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-win32.zip gg-win32 
#processZip groovy-grails-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-win32-x86_64.zip gg-win32.x86_64 

#createInstallerTar groovy-grails-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-macosx-cocoa.tar gg-macosx.cocoa groovy-grails-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-macosx-cocoa izpack.macinstall 1.5.0 32bit
#createInstallerTar groovy-grails-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-macosx-cocoa-x86_64.tar gg-macosx.cocoa.x86_64 groovy-grails-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-macosx-cocoa-x86_64 izpack.macinstall 1.6.0 64bit

#createInstallerTar groovy-grails-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-linux-gtk.tar gg-linux groovy-grails-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-linux-gtk izpack.linuxinstall 1.5.0 32bit
#createInstallerTar groovy-grails-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-linux-gtk-x86_64.tar gg-linux.x86_64 groovy-grails-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-linux-gtk-x86_64 izpack.linuxinstall 1.5.0 64bit

#createInstallerZip groovy-grails-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-win32.zip gg-win32 groovy-grails-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-win32 izpack.windowsinstall 1.5.0 32bit
#createInstallerZip groovy-grails-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-win32-x86_64.zip gg-win32.x86_64 groovy-grails-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-win32-x86_64 izpack.windowsinstall 1.5.0 64bit

#wrapInstallerDmg groovy-grails-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-macosx-cocoa-installer groovy-grails-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-macosx-cocoa-installer
#wrapInstallerDmg groovy-grails-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-macosx-cocoa-x86_64-installer groovy-grails-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-macosx-cocoa-x86_64-installer

#wrapInstallerSh groovy-grails-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-linux-gtk-x86_64-installer
#wrapInstallerSh groovy-grails-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-linux-gtk-installer

#wrapInstallerZip groovy-grails-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-win32-installer
#wrapInstallerZip groovy-grails-tool-suite-$RELEASE_VERSION-$ECLIPSE_VERSION-win32-x86_64-installer

#echo "...creating checksum files"
#for II in *tool-suite*; do 
#	openssl dgst -md5 $II >>$II.md5;
#	openssl dgst -sha1 $II >>$II.sha1;
#done

# /opt/build/sts/releng/com.springsource.sts.releng/epp/send-mail.sh
