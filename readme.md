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
mvn -P cg package sources:jar javadoc:jar gpg:sign
cd target
jar cvf bundle.jar <package_name>*
```

* Upload bundle.jar to oss.sonatype.org, release it.

### How to format code

As of now, the 0.7.2 version of Java parent POM requires all Java code to
match https://github.com/google/google-java-format. To format all Java code, run

```
mvn fmt:format
```

before submitting a change.