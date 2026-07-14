<script lang="ts">
	import type { GetRiderWithChildrenResponse } from "$lib/api/types/riders";

    let {
        rider
    }: {
        rider: GetRiderWithChildrenResponse
    } = $props();
</script>

<div class="flex flex-row bg-panel">
    <span class="grow">
        {rider.firstName} {rider.lastName}
    </span>
    <span class="grow">
        {rider.nation.flagEmoji}
    </span>
</div>
