clean:
	./gradlew clean

app :
	./gradlew build

run: app
	docker-compose build
	docker-compose up -d

all: clean run

push: clean app
	docker-compose build
	docker-compose push