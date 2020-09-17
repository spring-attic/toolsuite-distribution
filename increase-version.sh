update() {
	OLD=$1
	NEW=$2
	find . -name MANIFEST.MF | xargs sed -i '' -e "s/Bundle-Version: $OLD.qualifier/Bundle-Version: $NEW.qualifier/"
	find . -name feature.xml | xargs sed -i '' -e "s/$OLD.qualifier/$NEW.qualifier/"
	find . -name feature.xml | xargs sed -i '' -e "s/version=\"$OLD\"/version=\"$NEW\"/"
	find . -name category.xml | xargs sed -i '' -e "s/$OLD.qualifier/$NEW.qualifier/"
	find . -name pom.xml | xargs sed -i '' -e "s/<version>$OLD-SNAPSHOT<\/version>/<version>$NEW-SNAPSHOT<\/version>/"
	find . -name parent-pom.xml | xargs sed -i '' -e "s/<version>$OLD-SNAPSHOT<\/version>/<version>$NEW-SNAPSHOT<\/version>/"
	find . -name pom-parent.xml | xargs sed -i '' -e "s/<version>$OLD-SNAPSHOT<\/version>/<version>$NEW-SNAPSHOT<\/version>/"
	find . -name site.xml | xargs sed -i '' -e "s/$OLD.qualifier/$NEW.qualifier/"
	
	find . -name build.properties | xargs sed -i '' -e "s/$OLD/$NEW/"
	find . -name org.springsource.sts.ide.product | xargs sed -i '' -e "s/$OLD.qualifier/$NEW.qualifier/"
	find . -name org.springsource.ggts.ide.product | xargs sed -i '' -e "s/$OLD.qualifier/$NEW.qualifier/"
}

#mvn -Dtycho.mode=maven org.sonatype.tycho:tycho-versions-plugin:set-version -DnewVersion=1.4.1-SNAPSHOT

update $1 $2

