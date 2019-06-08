#!/bin/bash
test $(curl 139.162.20.221:8765/sum?a=1\&b=2) -eq 3