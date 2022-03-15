(ns weird.authentication
  {:author "David Harrigan"}
  (:require
   [buddy.auth.backends.httpbasic :refer [http-basic-backend]]
   [buddy.auth.middleware :refer [wrap-authentication]]))

(set! *warn-on-reflection* true)

;; PRIVATE FUNCTIONS AND DEFINITIONS ↓

(defn ^:private basic-authentication
  [_request]
  {:foo "bar"})

;; PUBLIC FUNCTIONS AND DEFINITIONS ↓

(def basic-authentication-middleware
  {:name ::basic-authentication
   :description "Wraps the handler in order to apply basic authentication to a protected resource."
   :wrap (fn [handler]
           (let [authenticator (http-basic-backend {:authfn basic-authentication})]
             (wrap-authentication handler authenticator)))})
