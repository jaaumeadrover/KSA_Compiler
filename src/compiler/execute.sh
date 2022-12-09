#!/bin/bash
( cd lexic ; jflex lexic.flex )
( cd sintactic ; java -jar java-cup-11b.jar sintactic_og.cup)