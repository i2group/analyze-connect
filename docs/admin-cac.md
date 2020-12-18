# Admin user interface

The admin user interface enables you to reload the gateway, preview configured services on the gateway, and configure schema mappings from a web browser.

To access the admin user interface, the user that you log in as must be a member of a user group that has the **i2:Administrator** command access control permission.

### Verify that your user is an Administrator

Check that your user has the **i2:Administrator** command access control
permission when using the example deployment:


1. Check that the **Administrator** user group in the `example-command-access-control.xml` file in
   `toolkit\configuration\fragments\opal-services\WEB-INF\classes` 
    has the `i2:Administrator` permission:
    ```xml
    <CommandAccessPermissions UserGroup="Administrator">
        <Permission Value="i2:Administrator" />
    </CommandAccessPermissions>
    ```

1. Check that your user belongs to the **Administrator** group in the `user.registry.xml` file in
   `i2analyze\deploy\wlp\usr\shared\config`:

    For example, if the name of the user is "Jenny", you should have the
    following:
    ```xml
    <group name="Administrator">
        <member name="Jenny"/>
    </group>
    ```
    If this does not exist, add your user to the Administrator user group to
    reflect the snippet above.



### Deploy with changes

If you've had to make changes to the `example-command-access-control.xml`
file above redeploy and restart the Liberty server.
```
setup -t deployLiberty
setup -t startLiberty
```

### Check access to admin user interface

In a web browser, go to `<i2-Analyze-URL>/admin` and check that you can access the admin user interface using your login details. Here, `<i2-Analyze-URL>` is the URL used to access your i2 Analyze deployment. For example, this might be `http://localhost:9082/opaldaod/admin`.

### More information

For more information about command access control, see [Controlling access to features](https://www.ibm.com/support/knowledgecenter/en/SSXVTH_latest/com.ibm.i2.eia.go.live.doc/controlling_access.html).
