package com.pdvsa.psp.mule.transformer;

import java.io.StringWriter;
import java.util.Collections;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.transformer.TransformerException;
import org.mule.config.i18n.MessageFactory;
import org.mule.transformer.AbstractTransformer;
import org.mule.transformer.types.SimpleDataType;

public final class VelocityPayloadTransformer extends AbstractTransformer
{

    private VelocityEngine velocityEngine;

    private String templateName;

    private Template template;

    public VelocityPayloadTransformer()
    {
        registerSourceType(Object.class);
        setReturnDataType(new SimpleDataType<String>(String.class));
    }

    public void setVelocityEngine(final VelocityEngine velocityEngine)
    {
        this.velocityEngine = velocityEngine;
    }

    public void setTemplateName(final String templateName)
    {
        this.templateName = templateName;
    }

    @Override
    public void initialise() throws InitialisationException
    {
        try
        {
            template = velocityEngine.getTemplate(templateName);
        }
        catch (final Exception e)
        {
            throw new InitialisationException(e, this);
        }
    }

    @Override
    protected Object doTransform(final Object payload, final String encoding) throws TransformerException
    {

        try
        {
            final StringWriter result = new StringWriter();

            template.merge(new VelocityContext(Collections.singletonMap("payload", payload)), result);

            return result.toString();

        }
        catch (final Exception e)
        {
            throw new TransformerException(
                MessageFactory.createStaticMessage("Can not transform message with template: " + template), e);
        }
    }
}