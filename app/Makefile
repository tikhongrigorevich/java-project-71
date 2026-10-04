.PHONY: build-dist run-dist clean lint lint-fix

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
