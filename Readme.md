# Running Tests

### TestNG parameters

- Running a test suite

`java -cp 'libs/*' org.testng.TestNG test-suite/flight-reservation.xml`

- Running a test suite with specific thread count

`java -cp 'libs/*' org.testng.TestNG -threadcount 2 test-suite/flight-reservation.xml`

- TestNG by default creates **test-output** directory. You can change it with **-d** option.

`java -cp 'libs/*' org.testng.TestNG -threadcount 2 -d result test-suite/flight-reservation.xml`

---

### System Properties

- To pass the browser option

`java -Dbrowser=chrome -cp 'libs/*' org.testng.TestNG test-suite/flight-reservation.xml`

- To run the tests using Selenium Grid outside container

`java -Dselenium.grid.enabled=true -Dselenium.hub.grid.hubHost=localhost -cp 'libs/*' org.testng.TestNG test-suite/flight-reservation.xml`

- To run the tests using Selenium Grid with specific thread count

`java -Dselenium.grid.enabled=true -Dselenium.hub.grid.hubHost=localhost -cp 'libs/*' org.testng.TestNG test-suite/flight-reservation.xml -threadcount 2`

- Building image from Dockerfile to use for test execution
`mvn clean package -DskipTests`
`docker build -t=ejetest/selenium .`

- Volume mapping to container so you can access the container files and folders
`docker run -it -v /c/Dev/selenium4-java-jenkins-docker-aws/results:/home/selenium-docker/test-output ejetest/selenium`

- Running test inside the container with volume mapping
`java -Dselenium.grid.enabled=true -Dselenium.hub.grid.hubHost=localhost -cp 'libs/*' org.testng.TestNG test-suite/flight-reservation.xml`

- Running test inside the container with no volume mapping
`java -cp 'libs/*' org.testng.TestNG test-suite/vendor-portal.xml`

- Running test outside container with config parameters and host
`docker run -e BROWSER=chrome -e HUB_HOST=172.27.112.1 -e TEST_SUITE=vendor-portal.xml -e THREAD_COUNT=2 ejetest/selenium`
