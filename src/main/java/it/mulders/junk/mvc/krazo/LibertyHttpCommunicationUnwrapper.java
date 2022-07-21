package it.mulders.junk.mvc.krazo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

import org.eclipse.krazo.core.HttpCommunicationUnwrapper;

import jakarta.annotation.Priority;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Priority(1)
public class LibertyHttpCommunicationUnwrapper implements HttpCommunicationUnwrapper {

    @Override
    public boolean supports(Object obj) {
        if (obj instanceof HttpServletRequest) {
            try {
                Class.forName("com.ibm.wsspi.webcontainer.WebContainerRequestState", false, obj.getClass().getClassLoader()); //$NON-NLS-1$
                return true;
            } catch (ClassNotFoundException e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public HttpServletRequest unwrapRequest(HttpServletRequest obj, Class<HttpServletRequest> type) {
        try {
            Class<?> requestStateClass = Class.forName("com.ibm.wsspi.webcontainer.WebContainerRequestState", false, obj.getClass().getClassLoader()); //$NON-NLS-1$
            Method getInstance = requestStateClass.getDeclaredMethod("getInstance", boolean.class); //$NON-NLS-1$
            Object requestState = getInstance.invoke(null, false);
            Objects.requireNonNull(requestState, "Unable to retrieve current WebContainerRequestState instance");
            Method getCurrentThreadsIExtendedRequest = requestStateClass.getDeclaredMethod("getCurrentThreadsIExtendedRequest"); //$NON-NLS-1$
            HttpServletRequest extendedRequest = (HttpServletRequest) getCurrentThreadsIExtendedRequest.invoke(requestState);
            Objects.requireNonNull(extendedRequest, "Unable to retrieve current IExtendedRequest from Liberty");
            return extendedRequest;
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException("Encountered exception when unwrapping Liberty request object", e);
        }
    }

    @Override
    public HttpServletResponse unwrapResponse(HttpServletResponse obj, Class<HttpServletResponse> type) {
        return obj;
    }

}