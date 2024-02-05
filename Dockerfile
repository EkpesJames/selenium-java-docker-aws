FROM bellsoft/liberica-openjdk-alpine
# This image will install java with it so we dont need to install java seperate.
# When the Dockerfile is build 'docker build -t=ejetest/selenium .',
# it will create an image

# workspace directory in the container
WORKDIR /home/selenium-docker

# Add required files to run test into /home/selenium-docker
ADD target/docker-resources ./

# Environment Variables
# BROWSER
# HUB_HOST
# TEST_SUITE
# THREAD_COUNT


# Run the test with ENTRY POINT
 ENTRYPOINT java -cp 'libs/*' \
            -Dselenium.grid.enabled=true \
            -Dselenium.hub.grid.hubHost=${HUB_HOST} \
            -Dbrowser=${BROWSER} \
            org.testng.TestNG \
            -threadcount ${THREAD_COUNT} \
            test-suite/${TEST_SUITE}