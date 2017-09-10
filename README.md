[![Build Status](https://travis-ci.org/salesforce/MavenParent.svg?branch=master)](https://travis-ci.org/salesforce/MavenParent)

Base Maven projects to get most the good stuff. Checkstyle (google-like), PMD, maven web site generation, BSD-3 licenses checking and deployment to /oss.sonatype.org.

Cloned from https://github.com/rexhoffman/MavenBase with some renaming and cleanup

Not Released yet, will be is 1.0.0

To use add to your maven project's pom.xml file:

```
    <parent>
        <groupId>com.salesforce.maven</groupId>
        <artifactId>MavenParent</artifactId>
        <version>1.0.0-SNAPSHOT</version>
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
* update the version of MavenAnalysisConf in ForceParent pom file
* update src/site/site.xml version of MavenAnalysisConf 


Generated Site:  https://salesforce.github.com/MavenParent/1.0.0-SNAPSHOT/index.html

