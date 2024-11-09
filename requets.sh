#!/usr/bin/zsh

curl -u user:85d57e72-1d74-440b-91f4-0ca6b4b9bf9c http://localhost:8080/test

echo -n user:85d57e72-1d74-440b-91f4-0ca6b4b9bf9c | base64

curl -H "Authorization: Basic key-base64" http://localhost:8080/test
