# CyclopsGroup organization

## Overview

This is a repository of CyclopsGroup organization level files. To learn more
about CyclopsGroup, visit http://www.cyclopsgroup.org.

## Maven POM files

### Java parent pom.xml

The parent Maven POM of all CyclopsGroup Java open-source projects.

### General parent pom.xml

The parent Maven POM of all CyclopsGroup open-source projects, also the parent
of the Java parent pom.xml.

## For developers

The following instructions are only for developers of CyclopsGroup open-source
projects. They are not applicable to everyone.

### How to release

* Checkout project, modify the version in POM. Make sure no SNAPSHOT version
exists in the POM file.
* Run maven command


```
mvn clean
mvn -P cg package source:jar javadoc:jar gpg:sign
cd target
jar cvf bundle.jar mypackage*
```

* Upload bundle.jar to oss.sonatype.org, release it.
* If anything went wrong, fix and repeat the process above.
* Tag the repository.

```
git tag --list
git tag mypackage_v_1_0_1
git push --tags
```

Depend on the platform, the GPG command does not always work smoothly. If it
doesn't work the output file can be signed manually.

```
gpg -ab mypackage-1.0.1.jar
gpg -ab mypackage-sources-1.0.1.jar
...
jar cvf bundle.jar mypackage*
```

### How to format code

As of now, the 0.7.2 version of Java parent POM requires all Java code to
match https://github.com/google/google-java-format. To format all Java code, run

```
mvn fmt:format
```

before submitting a change.
