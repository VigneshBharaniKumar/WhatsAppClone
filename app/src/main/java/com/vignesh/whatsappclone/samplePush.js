Parse.Cloud.define("samplePush", (request) => {

    return Parse.Push.send({
        channels: ["News"],
        data: {
            title: "Hello from the Cloud Code",
            alert: "Back4App rocks!",
        }
    }, { useMasterKey: true });
});