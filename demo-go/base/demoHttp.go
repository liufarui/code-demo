package main

import "net/http"

func main() {
	http.HandleFunc("/getDemo", func(w http.ResponseWriter, r *http.Request) {
		w.Write([]byte(`{"ResultCode":0,"ResultMsg":"success"}`))
	})
	http.ListenAndServe(":80", nil)
}
