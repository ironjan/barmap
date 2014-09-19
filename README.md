barmap
======

A rewrite of https://fsmi.uni-paderborn.de/kneipenplan/ using Clojure to enable user submitted content.

Plans
-----

Everything is just planned and open for discussion.

 * We will use the free plan of heroku.com
 * Language will be Clojure
 * The App will constist of multiple parts: REST API and Web UI
 * An Android app is also planned

Mockups
-------

mockup.epz is a rough draft of the UI. It was done using http://pencil.evolus.vn/

Planned REST-API
----------------
Assuming Accept-header "application/json". If none is given, html is 
served on default. 

```
GET / : List of locations
GET /{id} : Info for location with {id}
POST / : Add new location
PUT /: Declined
PUT /{id} : Update location with {id}
```

Test data
=========
Import the testdata using the following command in an psql-shell:
```COPY places (name, lat,lon,special, description) FROM 
'/abs/path/to/bars.csv' WITH DELIMITER ',' QUOTE '"' 
CSV;```
Lein Template
=============

The lines below were created by the "lein new heroku" command.

# barmap

A [Heroku](http://www.heroku.com) web app using Compojure.

This generated project has a few basics set up beyond the bare Compojure defaults:

* Cookie-backed session store
* Stack traces when in development
* Environment-based config via [environ](https://github.com/weavejester/environ)
* [HTTP-based REPL debugging](https://devcenter.heroku.com/articles/debugging-clojure) via [drawbridge](https://github.com/cemerick/drawbridge)

## Usage

To start a local web server for development you can either eval the
commented out forms at the bottom of `web.clj` from your editor or
launch from the command line:

    $ lein run -m barmap.web

Initialize a git repository for your project.

    $ git init
    $ git add .
    $ git commit -m "Initial commit."

You'll need the [heroku toolbelt](https://toolbelt.herokuapp.com)
installed to manage the heroku side of your app. Once it's installed,
get the app created:

    $ heroku apps:create barmap
    Creating barmap... done, stack is cedar
    http://barmap.herokuapp.com/ | git@heroku.com:barmap.git
    Git remote heroku added

You can deploy the skeleton project immediately:

    $ git push heroku master
    Writing objects: 100% (13/13), 2.87 KiB, done.
    Total 13 (delta 0), reused 0 (delta 0)

    -----> Heroku receiving push
    -----> Clojure app detected
    -----> Installing Leiningen
           Downloading: leiningen-2.0.0-preview7-standalone.jar
    [...]
    -----> Launching... done, v3
           http://barmap.herokuapp.com deployed to Heroku

    To git@heroku.com:barmap.git
     * [new branch]      master -> master

It's live! Hit it with `curl`:

    $ curl http://barmap.herokuapp.com
    ["Hello" :from Heroku]

The cookie-backed session store needs a session secret configured for encryption:

    $ heroku config:add SESSION_SECRET=$RANDOM_16_CHARS

## Remote REPL

The [devcenter article](https://devcenter.heroku.com/articles/debugging-clojure)
has a detailed explanation, but using the `repl` task from Leiningen
2.x lets you connect a REPL to a remote process over HTTP. The first
step is setting up credentials:

    $ heroku config:add REPL_USER=[...] REPL_PASSWORD=[...]

Then you can launch the REPL:

    $ lein repl :connect http://$REPL_USER:$REPL_PASSWORD@barmap.herokuapp.com/repl

Everything you enter will be evaluated remotely in the running dyno,
which can be very useful for debugging or inspecting live data.

## License

Copyright 2014 Jan Lippert

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
