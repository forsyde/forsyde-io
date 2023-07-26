VERSION 0.7

build-java-all:
    ARG jabba_jdk='amazon-corretto@1.17.0-0.35.1'
    FROM debian:latest
    WORKDIR java-workdir
    RUN apt-get update
    RUN apt-get install -y curl bash wget
    RUN curl -sL https://github.com/Jabba-Team/jabba/raw/main/install.sh | JABBA_COMMAND="install ${jabba_jdk} -o /jdk" bash
    ENV JAVA_HOME /jdk
    ENV PATH $JAVA_HOME/bin:$PATH
    COPY gradlew .
    COPY --dir gradle .
    COPY settings.gradle .
    COPY build.gradle .
    COPY --dir buildSrc .
    COPY --dir java-core .
    COPY --dir java-generator .
    COPY --dir java-libforsyde .
    COPY --dir java-graphviz .
    COPY --dir java-conversyde .
    COPY --dir java-sdf3 .
    COPY --dir java-bridge-forsyde-shallow .
    RUN sed -i 's/\r//g' gradlew # make sure that it is readable in linux by bash
    RUN ./gradlew build

publish-java-all:
    FROM +build-java-all
    RUN --secret GPG_SIGNING_KEY --secret GPG_SIGNING_PASSWORD ./gradlew build publish closeAndReleaseSonatypeStagingRepository
