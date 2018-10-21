clean:
	./gradlew clean

app :
	./gradlew build

run: app
	docker-compose build
	docker-compose up -d

all: clean run