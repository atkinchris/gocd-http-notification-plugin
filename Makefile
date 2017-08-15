build:
	./gradlew clean test assemble

docker:
	sh ./compose.sh
