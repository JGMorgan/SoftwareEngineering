/**
 * Created by Gajen on 4/16/2016.
 */

import java.lang.System;

public class UserController {

    private boolean exists;
    private boolean lockout;
    private int loginAttempts = 0;
    private final int MAX_LOGIN_ATTEMPTS = 4;
    private final int TIME_LOCKED_OUT = 300000;
    private long lockOutTime = 0;
    private long elapsedTime = 0;

    public void SubmitButton()
    {
        if(loginAttempts < MAX_LOGIN_ATTEMPTS && System.currentTimeMillis() > elapsedTime)
        {
            if(!exists)
            {
                System.out.println("Sorry, that username does not exist");
                loginAttempts++;
            }
            else
            {

            }

        }
        else if (!(loginAttempts < MAX_LOGIN_ATTEMPTS))
        {
            lockOutTime = 0;
            loginAttempts = 1;
            if(!exists)
            {
                System.out.println("Sorry, that username does not exist");
                loginAttempts++;
            }
            else
            {

            }
        }
        else
        {
            lockout = true;
            if(lockOutTime == 0)
            {
                lockOutTime = System.currentTimeMillis();
                elapsedTime = lockOutTime + TIME_LOCKED_OUT;
            }
            else
            {
                lockOutTime = 0;
            }

        }

    }



}
