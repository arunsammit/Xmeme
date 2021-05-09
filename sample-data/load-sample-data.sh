#!/bin/bash

mongoimport --db memesdb --collection meme --drop --jsonArray --file ./initial_list_of_meme_entity.json