#!/bin/sh

URL_TO_SCRAPE=$1

java \
    -Dlogging.level.my.repo.sainsburys=ERROR \
    -Dspring.main.banner-mode=off \
    -cp /app/resources:/app/classes:/app/libs/* my.repo.sainsburys.ScraperApplication "${URL_TO_SCRAPE}" | jq 
    