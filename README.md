### Status ###

[![Build Status](https://travis-ci.org/salesforce/MavenParent.svg?branch=master)](https://travis-ci.org/salesforce/MavenParent)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/e23ae73e84bc4b76b912afdeb679dc22)](https://www.codacy.com/app/rexhoffman/MavenParent?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=salesforce/MavenParent&amp;utm_campaign=Badge_Grade)
[![Maven Site](https://img.shields.io/badge/maven_site-1.0.19-green.svg)](https://opensource.salesforce.com/MavenParent/1.0.19/index.html)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.salesforce.maven/MavenParent/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.salesforce.maven/MavenParent)


Base Maven projects to get most the good stuff with minimal config on your part.
 * takari-lifecycle for incremental builds (now disabled by default as m2e-apt is fully capable of handle APT in eclipse).
 * Checkstyle (google-like).
 * PMD (overrideable, simple, example config).
 * maven web site generation.
 * site publishing to back to github pages.
 * BSD-3 or MIT licenses checking/formating (you can easily add your own).
 * jar file deployment to oss.sonatype.org.
 * comparisons against old versions of your jar for binary compatibility.  (Packages including the word "*.internal.*" are skipped.

Cloned from https://github.com/rexhoffman/MavenBase with some renaming and cleanup

To use add to your maven project's pom.xml file:

```xml
    <parent>
        <groupId>com.salesforce.maven</groupId>
        <artifactId>MavenParent</artifactId>
        <version>1.0.19</version>
    </parent>

    <properties>
        <github.owner>YOUR GITHUB GROUP OR USER</github.owner>
        <github.project>YOUR GITHUB PROJECT</github.project>
        <github.site.location>${project.version}/</github.site.location>
        <!-- set the below parameter to false once you've cut your first release and you want semantic verion checking -->
        <parameter.disable.semantic.versioning>true</parameter.disable.semantic.versioning>
    </properties>
```

Every child pom (module) of that pom should have a property of:

```xml
    <properties>
        <github.site.location>${project.version}/${artifactId}</github.site.location>
    </properties>
```

So that a multimodule project can publish it's sites back to github pages.

To release the website, you will need a github token to allow maven to push the site content back to git in your .m2/settings.xml.
To release artifacts to oss nexus you'll need credentials and and a server section to your .m2/settings.xml.  See example at the end of this file.


PMD, checkstyle (close to google's style) and a BSD-3 license are set by default, as is the use of takari-lifecycle,
for incremental build and proper APT support in eclipse.

## Configuration options

```xml
    <!-- explained above -->
    <github.domain>
    <github.owner>
    <github.project>
    
    <!-- these are currently set to use github, no need to touch -->
    <github.site.location>
    <github.site.server>
    
    <!-- regex to pass to checkstyle based on your selection -->
    <license.regex.BSD-3>
    <license.regex.CONFIDENTIAL>
    <license.regex.MIT>
    
    <maven.analysis.configuration>
    
    <!-- set to UTF-8 by default, no need to touch -->
    <outputEncoding>
    
    <!-- points to the example, google like checkstyle in Static Analysis jar -->
    <parameter.checkstyle.xml>
    
    <!-- explained above -->
    <parameter.disable.semantic.versioning>
    
    <!-- set for deploying to OSS nexus, no need to touch unless you want to use the staging repos -->
    <parameter.distribution.autorelease>
    <parameter.distribution.nexus>
    <parameter.distribution.releases>
    <parameter.distribution.skipstaging>
    <parameter.distribution.snapshots>
    
    <!-- a comma separated list of groupId:artifactId's of plugins you don't mind having as snapshots -- best used in development of plugins only -->
    <parameter.enforcer.unchecked.plugins.list>
    
    <!-- minimum version of java to run maven, default 1.8.0 -->
    <parameter.enforcer.min.java.version>
    
    <!-- minimum version of maven, to run this project default 3.5.0 -->
    <parameter.enforcer.min.maven.version> 
    
    <!-- can be used to turn off github site deployment -->
    <parameter.github.site.deploy.skip>
    
    <!-- can be used to enforce code coverage -->
    <parameter.jacoco.branch.coverage>
    <parameter.jacoco.instruction.coverage>
    
    <!-- currently set to ignore all packages containing an ".internal." segement.
    <parameter.japicmp.ignoredpackage>
    
    <!-- this is where you select one of licenses above.
           license.regex.BSD-3 or
           license.regex.CONFIDENTIAL or
           license.regex.MIT,
           for example:
             <parameter.license.regex>${license.regex.CONFIDENTIAL}</parameter.license.regex>
    -->    
    <parameter.license.regex>
    
    <!-- select a license for license:format, should match the license above. Select between: BSD-3, MIT, CONFIDENTIAL -->
    <parameter.license>
    
    <!-- owasp security plugin, non need to change -->
    <parameter.owasp.failOnCVSSLevelOrGreater>
    
    <!-- instructs pmd to perform incremental analysis -->
    <parameter.pmd.analysisCache>
    
    <!-- should a pmd violation fail the buid -->
    <parameter.pmd.fail>
    
    <!-- pmd config, touch if you want to change. -->
    <parameter.pmd.priority>
    <parameter.pmd.rules>
    
    <!-- if using github site deploy, leave this set to true.
    <parameter.standard.site.deploy.skip>
    
    <!-- should takari handle apt processing, and if so, should it always regenerate, 'proc' or 'procEX' 
         proc is default and indicates incremental processing.
         procEX means full processing on every invocation.
    -->
    <parameter.takari.proc>
    
    <!-- sets source encoding to UTF-8, override if you wish -->
    <project.build.sourceEncoding>
    
    <!-- versions of plugins and support jars, no need to touch -->
    <version.checkstyle.maven.plugin>
    <version.checkstyle>
    <version.clean.maven.plugin>
    <version.compiler.maven.plugin>
    <version.depedency.maven.plugin>
    <version.deployer.maven.plugin>
    <version.duplicate.finder.maven.plugin>
    <version.enforcer.maven.plugin>
    <version.findbugs.maven.plugin>
    <version.gpg.maven.plugin>
    <version.install.maven.plugin>
    <version.jacoco.maven.plugin>
    <version.japicmp.maven.plugin>
    <version.jar.maven.plugin>
    <version.javadoc.maven.plugin>
    <version.jdepend.maven.plugin>
    <version.jxr.maven.plugin>
    <version.mycila-license.maven.plugin>
    <version.nexus.staging.maven.plugin>
    <version.owasp.check.maven.plugin>
    <version.pmd.maven.plugin>
    <version.reports.maven.plugin>
    <version.resources.maven.plugin>
    <version.site.maven.plugin>
    <version.sortpom.maven.plugin>
    <version.surefire.maven.plugin>
    <version.takari.maven.plugin>
```    

## When releasing a new version of this project:

* mvn version:set
* update the version of MavenAnalysisConf in MavenParent pom file
* update src/site/site.xml version of MavenAnalysisConf 

Add credentials in your ~/.m2/settings.xml like:
    
```xml
    <!-- if you also will be deploying open source projects, and have an account at https://oss.sonatype.org/
       then include this section -->
    <server>
      <id>oss.sonatype.org</id>
      <username><!-- REPLACE WITH TOKEN FROM https://oss.sonatype.org/ --></username>
      <password><!-- REPLACE WITH TOKEN FROM https://oss.sonatype.org/ --></password>
    </server>
```

Note on Mac/Linux since gpg 2.1+, you'll want to assert your gpg tty is set, so deploy like:
```bash
mvn clean deploy -Dgpg.useagent=true
export GPG_TTY=$(tty)
```


## When deploying a maven generated site to github.com:

```
mvn site site-deploy
```

First create a personal access key in https://github.com/settings/tokens/ with the following permissions:
* public_repo
* user:email

and a section in your ~/.m2/settings.xml like:

```xml
    <server>
      <id>github.com</id>
      <password><!-- REPLACE WITH TOKEN FROM github.com --></password>
    </server>
```
