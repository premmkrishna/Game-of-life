Game of Life
======================
[A brief about the game](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life)

### Requirements

You will need to run these scripts on a Linux box and require:

 - A CLI tool
 - Git
 - Gradle
 - JDK 8+

### Usage
Make sure you are in the root directory.
Run the application with `./runGOL.sh`
    

### Building the Docker test containter

Firstly, you will need Docker installed and available in your path.

Change the application.properties as required.

To build the container, run `docker build -t gojek/game-of-life:latest .`

This will download the latest Docker image and run the build.

Once it's done, it should be available in your local Docker registry:

	$ docker images
	REPOSITORY            TAG     IMAGE ID      CREATED             VIRTUAL SIZE
	gojek/game-of-life   latest   2c69fd568bf9  About a minute ago   641MB
	
To run the image in container 

run `docker run --name game-of-life -it gojek/game-of-life`

You will see the output of application as per the configuration you have provided in application.properties

TODOS:
* Error handling for inputs
* Moves rules into its seperate class so it can be changed anytime
* Implement interactive and default modes
