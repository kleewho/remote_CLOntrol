(ns remote-clontrol.core.booking
  (:require [clojure.tools.logging :as log]
            [remote-clontrol.core.traxis :as traxis]
            [remote-clontrol.core.mds :as mds]))

(defn stb-type [{type :stb-type}]
  (log/info (str "stb-type " type))
  type)

(defmulti send-booking stb-type)

(defmethod send-booking :d4a [a]
  (mds/send-d4a a))

(defmethod send-booking :hzn [a]
  (mds/send-hzn a))

(defmethod send-booking :dawn [a]
  (traxis/send a))
