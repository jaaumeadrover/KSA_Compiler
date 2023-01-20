#!/bin/bash
( cd lexic ; java -jar jflex-1.6.1.jar lexic.flex )
( cd sintactic ; java -jar java-cup-11b.jar sintactic_og.cup)