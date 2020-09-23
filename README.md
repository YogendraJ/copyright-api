# copyright-api

[CopyRight API](https://app.swaggerhub.com/apis/YogendraJ/copyright-api/1.0.0#/) is mainly used for getting an input from a user / system in form of a String (e.g. "We really like the new security features of Google Cloud” and replaces few words with copyright symbol e.g. “We really like the new security features of Google Cloud©”.

Currently, the API supports replacing the following words:

1. Oracle -&gt; Oracle©
2. Google -&gt; Google©
3. Microsoft -&gt; Microsoft©
4. Amazon -&gt; Amazon©
5. Deloitte -&gt; Deloitte©

The Request format for the CopyRight API is as follows and expects: 

- Content-Type:application/json 
- ApiKey with header: "x-api-key" (Send an email to yjoshi@deloitte.com to recieve the ApiKey)

```json
{
  "request": "We really like the new security features of Google Cloud",
}
```
The Response format for the CopyRight API is:

```json
{
  "response": "We really like the new security features of Google© Cloud",
}
```

