#!/bin/bash

cd my-app
#ng build --prod --base-href /
ng build --prod
mv dist ../dist
cd ..
