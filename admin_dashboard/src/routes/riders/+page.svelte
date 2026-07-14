<script lang="ts">
	import Entity from '$lib/components/Entity.svelte';
	import { riderToEntity } from '$lib/middleware/rider.mapper';
	import type { EntityContent } from '$lib/types/entity';
	import type { RiderWithChildren } from '$lib/types/riders';
	import { onMount } from 'svelte';
	import type { PageData } from './$types';

    let { data }: { data: PageData } = $props();

    const dataToFields = (
        rider: RiderWithChildren
    ): EntityContent => {
        return riderToEntity(rider);
    }

    onMount(() => {
        console.log(data.riders)
    })
</script>

<div class="space-y-2 flex overflow-hidden relative flex-col p-2 w-full h-full">
    <div class="flex flex-1 flex-col min-h-0 space-y-2">
        <div class="flex flex-row shrink-0 bg-mauve-0">
            <span class="font-bold text-center basis-1/3">Name</span>
            <span class="font-bold text-center basis-1/3">Nation</span>
            <span class="font-bold text-center basis-1/3">Team</span>
        </div>
        <div class="flex overflow-y-scroll flex-col flex-1 min-h-0 space-y-1">
            {#each data.riders.map((rider) => riderToEntity(rider)) as rider }
                <Entity>
                    <span class="text-center basis-1/3">{rider.values[0]}</span>
                    <span class="text-center basis-1/3">{rider.values[1]}</span>
                    <span class="text-center basis-1/3">{rider.values[2]}</span>
                </Entity>
            {/each}
        </div>
    </div>
</div>
