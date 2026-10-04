.PHONY: build build-dist run-dist clean lint lint-fix test

build:
	./gradlew clean build

build-dist:
	./gradlew clean installDist

run-dist: build-dist
	./build/install/app/bin/app

clean:
	./gradlew clean

lint:
	./gradlew spotlessCheck

lint-fix:
	./gradlew spotlessApply

test:
	./gradlew test
