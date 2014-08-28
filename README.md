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
