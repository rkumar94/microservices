FROM ubuntu:14.04
MAINTAINER Danil Kuznetsov <kuznetsov.danil.v@gmail.com>

ENV DEBIAN_FRONTEND=noninteractive

# UTF-8 locale
RUN locale-gen en_US en_US.UTF-8
RUN dpkg-reconfigure locales
ENV LC_ALL en_US.UTF-8
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US.UTF-8

# basic linux packages
RUN apt-get update
RUN apt-get install -y wget bcrypt curl
RUN apt-get install -y unzip zip
RUN apt-get install -y gnupg gnupg2
RUN apt-get install -y bsdmainutils
RUN apt-get install -y libxml2-utils
RUN apt-get install -y build-essential
RUN apt-get install -y automake autoconf

# add webupd8 repository
RUN \
	echo "deb http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main" \
		| tee /etc/apt/sources.list.d/webupd8team-java.list && \
    echo "deb-src http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main" \
    	| tee -a /etc/apt/sources.list.d/webupd8team-java.list  && \
    apt-key adv --keyserver keyserver.ubuntu.com --recv-keys EEA14886  && \
    apt-get update

# auto accept oracle jdk license
RUN echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections

# install oracle java8
RUN apt-get install -y --force-yes oracle-java8-installer oracle-java8-set-default

# define commonly used JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

# install maven
ENV MAVEN_VERSION 3.3.9
ENV M2_HOME "/usr/local/apache-maven/apache-maven-${MAVEN_VERSION}"
RUN wget --quiet "http://mirror.dkd.de/apache/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz" && \
  mkdir -p /usr/local/apache-maven && \
  mv "apache-maven-${MAVEN_VERSION}-bin.tar.gz" /usr/local/apache-maven && \
  tar xzvf "/usr/local/apache-maven/apache-maven-${MAVEN_VERSION}-bin.tar.gz" -C /usr/local/apache-maven/ && \
  update-alternatives --install /usr/bin/mvn mvn "${M2_HOME}/bin/mvn" 1 && \
  update-alternatives --config mvn

# install gradle
ENV GRADLE_VERSION 2.14.1
ENV GRADLE_HOME "/usr/local/gradle/gradle-${GRADLE_VERSION}"
RUN wget --quiet "https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip" && \
	mkdir -p /usr/local/gradle && \
	mv "gradle-${GRADLE_VERSION}-bin.zip" /usr/local/gradle/ && \
	unzip "/usr/local/gradle/gradle-${GRADLE_VERSION}-bin.zip" -d /usr/local/gradle/ && \
	update-alternatives --install /usr/bin/gradle gradle "${GRADLE_HOME}/bin/gradle" 1 && \
    update-alternatives --config gradle

# install nodejs
RUN curl -sL https://deb.nodesource.com/setup | sudo bash -
RUN apt-get install -y nodejs
RUN npm install -g npm

# clean up
RUN rm -rf /var/cache/oracle-jdk8-installer  && \
    apt-get clean  && \
    rm -rf /var/lib/apt/lists/*