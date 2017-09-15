### Status ###

[![Build Status](https://travis-ci.org/salesforce/MavenParent.svg?branch=master)](https://travis-ci.org/salesforce/MavenParent)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/e23ae73e84bc4b76b912afdeb679dc22)](https://www.codacy.com/app/rexhoffman/MavenParent?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=salesforce/MavenParent&amp;utm_campaign=Badge_Grade)
[![Maven Site](https://img.shields.io/badge/MavenSite-1.0.0--SNAPSHOT-green.svg)](https://salesforce.github.com/MavenParent/1.0.0-SNAPSHOT/index.html)

Base Maven projects to get most the good stuff. Checkstyle (google-like), PMD, maven web site generation, BSD-3 licenses checking and deployment to /oss.sonatype.org.

Cloned from https://github.com/rexhoffman/MavenBase with some renaming and cleanup

To use add to your maven project's pom.xml file:

```
    <parent>
        <groupId>com.salesforce.maven</groupId>
        <artifactId>MavenParent</artifactId>
        <version>1.0.0</version>
    </parent>
```

```
    <properties>
        <github.owner>YOUR GITHUB GROUP OR USER</github.owner>
        <github.project>YOUR GITHUB PROJECT</github.project>
        <github.site.location>${project.version}/</github.site.location>
    </properties>
```

Every child pom (module) of that pom should have a property of:

```
    <properties>
        <github.site.location>${project.version}/${artifactId}</github.site.location>
    </properties>
```

So that a multimodule project can publish it's sites back to github pages.

To release the website, you will need a github token to allow maven to push the site content back to git.

PMD, checkstyle (close to google's style) and a BSD-3 license are set by default, as is the use of takari-lifecycle,
for incremental build and proper APT support in eclipse, so please use ```<packaging>takari-jar</packaging>``` for all your java projects.


When releasing a new version of this project:

* mvn version:set
* update the version of MavenAnalysisConf in MavenParent pom file
* update src/site/site.xml version of MavenAnalysisConf 
