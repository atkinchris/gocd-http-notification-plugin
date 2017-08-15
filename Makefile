build:
	./gradlew clean assemble

docker:
	sh ./compose.sh

copy:
	mkdir -p ./go/data/plugins/external
	cp ./build/libs/*.jar ./go/data/plugins/external
