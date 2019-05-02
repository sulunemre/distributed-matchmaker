# Homework 4
Write an RMI server that matches clients who are looking for matches.
The server should provide a single method: `String match(String name, int timeoutSecs)`. The first parameter is
the unique name of the client who wants to be matched. The second parameter is the time out value (how long the client
can wait). The method returns the name of another client who is currently looking for matches. If a match cannot be
found in `timeoutSecs` seconds, then the method should return null.
Once two clients get each others' names, they should get a remote reference to each other and wait for a `handshake()`
call. Use the client name lexicographical order to order the `handshakes()`.
Here is an example order of events:
- A asks the server for a match
- B asks the server for a match
- B's call returns with A's name
- A's call returns with B's name
- B waits for A's handshake
- A sends a handshake to B
- A waits for B's handshake
- B sends a handshake to A

Extra points if your code does not include any sleep calls.