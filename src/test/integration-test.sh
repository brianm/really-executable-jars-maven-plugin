#!/usr/bin/env bash
# we expect to be called from the plugin project root
set -e

expected_jar="target/testapp-1.0.jar"

cd target/test-classes/test-project

echo "### standard run with default script"
mvn -B -ntp clean package
test -x $expected_jar || (echo "$expected_jar not found or not executable!" && exit 1)
$expected_jar | grep -i "hello" || (echo "$expected_jar did not give expected response" && exit 1)

echo "### run with alternative script"
mvn -B -ntp -Dreally-executable-jar.scriptFile=script.sh clean package
test -x $expected_jar || (echo "$expected_jar not found or not executable!" && exit 1)
$expected_jar | grep -i "fake" || (echo "$expected_jar did not give expected response" && exit 1)

echo "### run with additional flags"
mvn -B -ntp -Dreally-executable-jar.flags=-version clean package
test -x $expected_jar || (echo "$expected_jar not found or not executable!" && exit 1)
$expected_jar 2> >(grep -i "jdk" || echo "$expected_jar did not give expected response" && exit 1)

echo "### done!"
