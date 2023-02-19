#!/usr/bin/env bash
# we expect to be called from the plugin project root
set -e

maven="mvn"
expected_jar="target/testapp-1.0.jar"
secondary_jar="target/testapp-1.0-secondary.jar"

cd target/test-classes/test-project

echo "### standard run with default script"
$maven -B -ntp clean package
test -x $expected_jar || (echo "$expected_jar not found or not executable!" ; exit 1)
test -x $secondary_jar || (echo "$secondary_jar not found or not executable!" ; exit 1)
$expected_jar | grep -i "hello" || (echo "$expected_jar did not give expected response" ; exit 1)
$secondary_jar | grep -i "hello" || (echo "$secondary_jar did not give expected response" ; exit 1)

echo "### run with alternative script"
$maven -B -ntp -Dreally-executable-jar.scriptFile=script.sh clean package
test -x $expected_jar || (echo "$expected_jar not found or not executable!" ; exit 1)
$expected_jar | grep -i "fake" || (echo "$expected_jar did not give expected response" ; exit 1)
$secondary_jar | grep -i "fake" || (echo "$secondary_jar did not give expected response" ; exit 1)

echo "### run with additional flags"
$maven -B -ntp -Dreally-executable-jar.flags="-Dapp.greeting=Salut" clean package
test -x $expected_jar || (echo "$expected_jar not found or not executable!" ; exit 1)
$expected_jar | grep -i "salut" || (echo "$expected_jar did not give expected response" ; exit 1)
$secondary_jar | grep -i "salut" || (echo "$secondary_jar did not give expected response" ; exit 1)

echo "### run with classifier for artifact"
$maven -B -ntp -Dreally-executable-jar.classifier=secondary clean package
test -x $expected_jar && (echo "$expected_jar executable but shouldn't be!" ; exit 1)
test -x $secondary_jar || (echo "$secondary_jar not found or not executable!" ; exit 1)
$secondary_jar | grep -i "hello" || (echo "$secondary_jar did not give expected response" ; exit 1)

echo "### run with specific file for artifact"
$maven -B -ntp -Dreally-executable-jar.inputFile="$secondary_jar" clean package
test -x $expected_jar && (echo "$expected_jar executable but shouldn't be!" ; exit 1)
test -x $secondary_jar || (echo "$secondary_jar not found or not executable!" ; exit 1)
$secondary_jar | grep -i "hello" || (echo "$secondary_jar did not give expected response" ; exit 1)

echo "### run standalone with specific file for artifact"
$maven -B -ntp -Dreally-executable-jar.classifier=secondary clean package
test -x $expected_jar && (echo "$expected_jar executable but shouldn't be!" ; exit 1)
test -x $secondary_jar || (echo "$secondary_jar not found or not executable!" ; exit 1)
$maven -B -ntp -Dreally-executable-jar.inputFile="$expected_jar" really-executable-jar:really-executable-jar
test -x $expected_jar || (echo "$expected_jar not found or not executable!" ; exit 1)
$expected_jar | grep -i "hello" || (echo "$expected_jar did not give expected response" ; exit 1)

echo "### run with programFile set but multiple artifacts"
$maven -B -ntp -Dreally-executable-jar.programFile=bananas clean package && (echo "this should have failed" ; exit 1)

echo "### run with programFile set and a single inputFile"
really_expected_jar="target/bananas"
$maven -B -ntp -Dreally-executable-jar.inputFile="$expected_jar" -Dreally-executable-jar.programFile=bananas clean package
test -x $expected_jar && (echo "$expected_jar executable but shouldn't be!" ; exit 1)
test -x $secondary_jar && (echo "$secondary_jar executable but shouldn't be!" ; exit 1)
$really_expected_jar | grep -i "hello" || (echo "$really_expected_jar did not give expected response" ; exit 1)

echo "### done!"
