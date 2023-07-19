VERSION 0.7

build-java-all:
    ARG jabba_jdk='amazon-corretto@1.17.0-0.35.1'
    FROM ubuntu:latest
    WORKDIR java-workdir
    RUN apt-get update
    RUN apt-get install -y curl bash wget
    RUN curl -sL https://github.com/shyiko/jabba/raw/master/install.sh | JABBA_COMMAND="install zulu@13 -o /jdk" bash
    ENV JAVA_HOME /jdk
    ENV PATH $JAVA_HOME/bin:$PATH
    COPY gradlew gradlew
    COPY --dir gradle gradle
    COPY settings.gradle settings.gradle
    COPY build.gradle build.gradle
    COPY --dir java-core .
    COPY --dir java-generator .
    COPY --dir java-libforsyde .
    COPY --dir java-graphviz .
    COPY --dir java-conversyde .
    COPY --dir java-sdf3 .
    COPY --dir java-bridge-forsyde-shallow .
    RUN sed -i 's/\r//g' gradlew # make sure that it is readable in linux by bash
    RUN ./gradlew build
