(ns metabase.test.data.dataset-definitions
  "Definitions of various datasets for use in tests with `with-temp-db`."
  (:require [clojure.tools.reader.edn :as edn]
            [metabase.test.data.interface :refer [def-database-definition]]))


;; ## Datasets

(def ^:private ^:const edn-definitions-dir "./test/metabase/test/data/dataset_definitions/")

;; TODO - move this to interface
;; TODO - make rows be lazily loadable for DB definitions from a file
(defmacro ^:private def-database-definition-edn [dbname]
  `(def-database-definition ~dbname
     ~@(edn/read-string (slurp (str edn-definitions-dir (name dbname) ".edn")))))

;; The O.G. "Test Database" dataset
(def-database-definition-edn test_data)

;; Times when the Toucan cried
(def-database-definition-edn sad_toucan_incidents)

;; Places, times, and circumstances where Tupac was sighted
(def-database-definition-edn tupac_sightings)

(def-database-definition-edn geographical_tips)

;; A tiny dataset where half the NON-NULL values are valid URLs
(def-database-definition-edn half_valid_urls)

;; A very tiny dataset with a list of places and a booleans
(def-database-definition-edn places_cam_likes)

;; A small dataset with users and a set of messages between them. Each message has *2* foreign keys to user --
;; sender and reciever -- allowing us to test situations where multiple joins for a *single* table should occur.
(def-database-definition-edn avian_singles)
