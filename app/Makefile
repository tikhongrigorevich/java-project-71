.PHONY: build build-dist run-dist clean lint lint-fix test

build:
	./gradlew clean build

build-dist:
	./gradlew clean installDist

run-dist: build-dist
	./build/install/app/bin/app

run-json:
	./build/install/app/bin/app -f plain src/test/resources/fixtures/file1.json src/test/resources/fixtures/file2.json

run-plain:
	./build/install/app/bin/app -f plain src/test/resources/fixtures/fileStylish1.json src/test/resources/fixtures/fileStylish2.json

run-stylish:
	./build/install/app/bin/app -f stylish src/test/resources/fixtures/fileStylish1.json src/test/resources/fixtures/fileStylish2.json

run-yml:
	./build/install/app/bin/app -f plain src/test/resources/fixtures/file1.yml src/test/resources/fixtures/file2.yml

clean:
	./gradlew clean

lint:
	./gradlew spotlessCheck

lint-fix:
	./gradlew spotlessApply

test:
	./gradlew test
