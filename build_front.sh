#!/bin/bash

cd my-app
#ng build --prod --base-href /
ng build --prod
rm -rf ../dist
mv dist ../dist
cd ..
