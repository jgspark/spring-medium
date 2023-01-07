import http from 'k6/http';
import {sleep} from 'k6';

export default function () {
    const url = "http://localhost:8080/k6"
    http.get(url)
    sleep(1)
}
