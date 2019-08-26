# Duct server.http.chester

Integrant multimethods for running a [Chester][] server for the [Duct][]
framework.

[Chester]: https://github.com/CyCognito/chester
[duct]: https://github.com/duct-framework/duct

## Installation

To install, add the following to your project `:dependencies`:

    [viebel/server.http.chester "0.1.0"]

## Usage

The multimethods dispatch off the `:duct.server.http/chester` key, which
is derived from `:duct.server/http`. The corresponding value is a map
of options for the [Chester][], plus a `:handler` key that
takes a handler function.

For example:

```clojure
{:duct.server.http/chester
 {:port    3000
  :api-key "secret"
  :context "my-app"
  :handler (fn [request]
             {:status  200
              :headers {"Content-Type" "text/plain"}
              :body    "Hello World"})}}
```

A `:logger` key may also be specified, which will be used to log when
the server starts and when it stops. The value of the key should be an
implementation of the `duct.logger/Logger` protocol from the
[duct.logger][] library

[duct.logger]: https://github.com/duct-framework/logger
