package com.netflix.imflibrary.st2067_2;

import com.netflix.imflibrary.IMFErrorLoggerImpl;
import com.netflix.imflibrary.utils.FileByteRangeProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import testUtils.TestHelper;

import java.io.File;
import java.util.UUID;

@Test(groups = "unit")
public class IMFEssenceComponentVirtualTrackTest
{
    @Test
    public void testEssenceComponentVirtualTrack_2013() throws Exception
    {
        File inputFile = TestHelper.findResourceByPath("TestIMP/Netflix_Sony_Plugfest_2015/CPL_BLACKL_202_HD_REC709_178_LAS_8fad47bb-ab01-4f0d-a08c-d1e6c6cb62b4_corrected.xml");
        ApplicationComposition applicationComposition = ApplicationCompositionFactory.getApplicationComposition(new FileByteRangeProvider(inputFile), new IMFErrorLoggerImpl());
        Assert.assertTrue(ApplicationComposition.isCompositionPlaylist(new FileByteRangeProvider(inputFile)));
        Assert.assertTrue(applicationComposition.toString().length() > 0);
        Assert.assertEquals(applicationComposition.getEditRate().getNumerator().longValue(), 24000);
        Assert.assertEquals(applicationComposition.getEditRate().getDenominator().longValue(), 1001);
        Assert.assertEquals(applicationComposition.getUUID(), UUID.fromString("8fad47bb-ab01-4f0d-a08c-d1e6c6cb62b4"));

        Assert.assertEquals(applicationComposition.getVirtualTracks().size(), 4);

        IMFEssenceComponentVirtualTrack virtualTrack = applicationComposition.getVideoVirtualTrack();

        Assert.assertEquals(virtualTrack.getTrackFileResourceList().size(), 7);
    }

    @Test
    public void testEssenceComponentVirtualTrackEquivalent_2013() throws Exception
    {
        File inputFile = TestHelper.findResourceByPath("TestIMP/Netflix_Sony_Plugfest_2015/CPL_BLACKL_202_HD_REC709_178_LAS_8fad47bb-ab01-4f0d-a08c-d1e6c6cb62b4_corrected.xml");
        ApplicationComposition applicationComposition1 = ApplicationCompositionFactory.getApplicationComposition(inputFile, new IMFErrorLoggerImpl());
        ApplicationComposition applicationComposition2 = ApplicationCompositionFactory.getApplicationComposition(inputFile, new IMFErrorLoggerImpl());


        IMFEssenceComponentVirtualTrack virtualTrack1 = applicationComposition1.getVideoVirtualTrack();
        IMFEssenceComponentVirtualTrack virtualTrack2 = applicationComposition2.getVideoVirtualTrack();

        Assert.assertTrue(virtualTrack1.equivalent(virtualTrack2));
    }

    @Test
    public void testEssenceComponentVirtualTrack_2016() throws Exception
    {
        File inputFile = TestHelper.findResourceByPath("TestIMP/Netflix_Sony_Plugfest_2015/CPL_BLACKL_202_HD_REC709_178_LAS_2016_8fad47bb-ab01-4f0d-a08c-d1e6c6cb62b4_corrected.xml");
        ApplicationComposition applicationComposition = ApplicationCompositionFactory.getApplicationComposition(inputFile, new IMFErrorLoggerImpl());
        Assert.assertTrue(ApplicationComposition.isCompositionPlaylist(new FileByteRangeProvider(inputFile)));
        Assert.assertTrue(applicationComposition.toString().length() > 0);
        Assert.assertEquals(applicationComposition.getEditRate().getNumerator().longValue(), 24000);
        Assert.assertEquals(applicationComposition.getEditRate().getDenominator().longValue(), 1001);
        Assert.assertEquals(applicationComposition.getUUID(), UUID.fromString("8fad47bb-ab01-4f0d-a08c-d1e6c6cb62b4"));

        Assert.assertEquals(applicationComposition.getVirtualTracks().size(), 4);

        IMFEssenceComponentVirtualTrack virtualTrack = applicationComposition.getVideoVirtualTrack();

        Assert.assertEquals(virtualTrack.getTrackFileResourceList().size(), 7);

        for( Composition.VirtualTrack track : applicationComposition.getVirtualTracks()) {
            if(track instanceof IMFEssenceComponentVirtualTrack)
            {
                IMFEssenceComponentVirtualTrack essenseTrack = (IMFEssenceComponentVirtualTrack) track;
                for (IMFTrackFileResourceType resource : essenseTrack.getTrackFileResourceList()) {
                    Assert.assertEquals(resource.getHashAlgorithm(), "http://www.w3.org/2000/09/xmldsig#sha1");
                }
            }
        }
    }

    @Test
    public void testEssenceComponentVirtualTrackEquivalent_2016() throws Exception
    {
        File inputFile = TestHelper.findResourceByPath("TestIMP/Netflix_Sony_Plugfest_2015/CPL_BLACKL_202_HD_REC709_178_LAS_2016_8fad47bb-ab01-4f0d-a08c-d1e6c6cb62b4_corrected.xml");
        ApplicationComposition applicationComposition1 = ApplicationCompositionFactory.getApplicationComposition(inputFile, new IMFErrorLoggerImpl());
        ApplicationComposition applicationComposition2 = ApplicationCompositionFactory.getApplicationComposition(inputFile, new IMFErrorLoggerImpl());


        IMFEssenceComponentVirtualTrack virtualTrack1 = applicationComposition1.getVideoVirtualTrack();
        IMFEssenceComponentVirtualTrack virtualTrack2 = applicationComposition2.getVideoVirtualTrack();

        Assert.assertTrue(virtualTrack1.equivalent(virtualTrack2));
    }


    @Test
    public void testZeroResourceDuration() throws Exception
    {
        File inputFile = TestHelper.findResourceByPath("TestIMP/Netflix_Sony_Plugfest_2015/CPL_BLACKL_202_HD_REC709_178_LAS_zero_resource_duration_track_8fad47bb-ab01-4f0d-a08c-d1e6c6cb62b4.xml");
        IMFErrorLoggerImpl errorLogger = new IMFErrorLoggerImpl();
        ApplicationComposition applicationComposition = ApplicationCompositionFactory.getApplicationComposition(inputFile, new IMFErrorLoggerImpl());
        Assert.assertTrue(ApplicationComposition.isCompositionPlaylist(new FileByteRangeProvider(inputFile)));
        Assert.assertTrue(applicationComposition.toString().length() > 0);
        Assert.assertEquals(applicationComposition.getEditRate().getNumerator().longValue(), 24000);
        Assert.assertEquals(applicationComposition.getEditRate().getDenominator().longValue(), 1001);
        Assert.assertEquals(applicationComposition.getUUID(), UUID.fromString("8fad47bb-ab01-4f0d-a08c-d1e6c6cb62b4"));

        Assert.assertEquals(applicationComposition.getVirtualTracks().size(), 4);

        IMFEssenceComponentVirtualTrack virtualTrack = applicationComposition.getVideoVirtualTrack();

        for(IMFBaseResourceType resource: virtualTrack.getTrackFileResourceList())
        {
            Assert.assertTrue(resource.getSourceDuration().longValue() > 0);
        }
    }
}
